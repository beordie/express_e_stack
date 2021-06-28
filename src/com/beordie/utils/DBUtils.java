package com.beordie.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Classname DBUtils
 * @Description 数据库连接工具类
 * @Date 2021/5/26 19:14
 * @Created 30500
 */
public class DBUtils {
    public static String userName;
    private static String password;
    private static String url;
    private static String driverName;
    private Integer count;
    private Connection connection;
    protected PreparedStatement statement;
    public ResultSet resultSet;

    public PreparedStatement getStatement() {
        return statement;
    }

    private static DruidDataSource source = new DruidDataSource();

    // 获取初始化连接参数
    static {
        //德鲁伊
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        driverName = bundle.getString("driverClassName");
        url = bundle.getString("url");
        userName = bundle.getString("username");
        password = bundle.getString("password");

        source.setUsername(userName);
        source.setPassword(password);
        source.setUrl(url);
        source.setDriverClassName(driverName);
    }

    /**
     * @description 拿到连接对象
     * @author 30500
     * @date 2021/5/26 20:59
     * @type []
     * @return java.sql.Connection
     */
    protected Connection getConnection() {
        try {
            this.connection = source.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.connection;
    }

    /**
     * @description 拿到预处理对象
     * @author 30500
     * @date 2021/5/26 21:00
     * @type [java.lang.String]
     * @return java.sql.PreparedStatement
     */
    protected PreparedStatement getStatement(String sql) {
        try {
            // 第二个参数表示可返回新增时的自增长 ID 号
            this.statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.statement;
    }

    /**
     * @description 设置占位符参数
     * @author 30500
     * @date 2021/5/26 21:00
     * @type [java.util.List]
     * @return void
     */
    protected void setparameters(List parameters) {
        if (parameters == null || parameters.size() == 0)
            return;
        for (int i = 0; i < parameters.size(); i++) {
            try {
                this.statement.setObject(i + 1, parameters.get(i));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * @description 数据库 增 删 改
     * @author 30500
     * @date 2021/5/26 21:00
     * @type [java.lang.String, java.util.List]
     * @return int
     */
    protected int update(String sql, List parameters) {
        getStatement(sql);
        setparameters(parameters);
        try {
            this.count = this.statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.count;
    }

    /**
     * @description 数据库查询
     * @author 30500
     * @date 2021/5/26 21:00
     * @type [java.lang.String, java.util.List]
     * @return java.sql.ResultSet
     */
    protected ResultSet query(String sql, List parameters) {
        getStatement(sql);
        setparameters(parameters);
        try {
            this.resultSet = this.statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.resultSet;
    }

    /**
     * @description 关闭所有连接
     * @author 30500
     * @date 2021/5/26 21:00
     * @type []
     * @return void
     */
    protected void close() {
        try {
            if (this.resultSet != null)
                this.resultSet.close();
            if (this.statement != null)
                this.statement.close();
            if (this.connection != null)
                this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
