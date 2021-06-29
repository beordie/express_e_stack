package com.beordie.dao.impl;

import com.beordie.dao.IAdminDao;
import com.beordie.model.Admin;
import com.beordie.model.Users;
import com.beordie.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname AdminDaoImpl
 * @Description 管理员
 * @Date 2021/6/29 10:46
 * @Created 30500
 */
public class AdminDaoImpl extends DBUtils implements IAdminDao {
    @Override
    public Admin getByNamePass(String name, String password) {
        // 构造参数
        List<String> params = new ArrayList<>();
        params.add(name);
        params.add(password);

        Admin admin = null;
        resultSet = super.query(SQL_FIND_NAME_PASSWORD, params);
        try {
            // 填充查询结果
            while (resultSet.next()) {
                admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(name);
                admin.setPassword(password);
                admin.setCreateTime(resultSet.getDate("createtime"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setNumber(resultSet.getString("number"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return admin;
    }

    @Override
    public int updateIpLoginTime(int id, String ip, Date time) {
        // 构造参数
        List<Object> params = new ArrayList<>();
        params.add(ip);
        params.add(time);
        params.add(id);

        int result = 0;
        try {
            // 更新数据库
            result = super.update(SQL_UPDATE_IP_LOGINTIME, params);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }
}
