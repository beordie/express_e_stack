package com.beordie.dao.impl;

import com.beordie.dao.IUserDao;
import com.beordie.model.Users;
import com.beordie.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname UserDaoImpl
 * @Description TODO
 * @Date 2021/6/25 16:21
 * @Created 30500
 */
public class UserDaoImpl extends DBUtils implements IUserDao {
    @Override
    public Users getByNamePass(String name, String password) {
        // 构造参数
        List<String> params = new ArrayList<>();
        params.add(name);
        params.add(password);

        Users user = null;
        resultSet = super.query(SQL_FIND_NAME_PASSWORD, params);
        try {
            // 填充查询结果
            while (resultSet.next()) {
                user = new Users();
                user.setUserId(resultSet.getInt("id"));
                user.setUserName(name);
                user.setPassword(password);
                user.setCreateTime(resultSet.getTime("createtime"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return user;
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
