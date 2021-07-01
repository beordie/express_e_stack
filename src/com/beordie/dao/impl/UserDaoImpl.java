package com.beordie.dao.impl;

import com.beordie.dao.IUserDao;
import com.beordie.model.Express;
import com.beordie.model.Users;
import com.beordie.utils.DBUtils;

import java.sql.SQLException;
import java.util.*;

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
                user.setCreateTime(resultSet.getDate("createtime"));
                user.setUserPhone(resultSet.getString("phone"));
                user.setNumber(resultSet.getString("number"));
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

    @Override
    public int insert(String username, String password, String phone, String number, int identity) {
        List<Object> params = new ArrayList<>();
        params.add(username);
        params.add(password);
        params.add(phone);
        params.add(number);
        params.add(identity);
        int result = 0;
        try {
            result = super.update(SQL_INSERT, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }

    @Override
    public int update(String username, String password, String phone, String number, int id) {
        List<Object> params = new ArrayList<>();
        params.add(username);
        params.add(password);
        params.add(phone);
        params.add(number);
        params.add(id);
        int result = 0;
        try {
            result = super.update(SQL_UPDATE, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }

    @Override
    public List<Map<String, String>> getAllRank() {
        List<Map<String, String>> result = new ArrayList<>();

        try {
            resultSet = super.query(SQL_GET_ALL_RANK, null);
            while (resultSet.next()) {
                Map<String, String> temp = new HashMap<>();
                temp.put("name", resultSet.getString(1));
                temp.put("total", resultSet.getString(2));
                result.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }

    @Override
    public Users getByPhone(String phone, int identity) {
        List<Object> params = new ArrayList<>();
        params.add(phone);
        params.add(identity);
        Users users = null;
        try {
            resultSet = super.query(SQL_FIND_BY_PHONE, params);
            if (resultSet.next()) {
                users = new Users();
                users.setId(resultSet.getInt("id"));
                users.setUserName(resultSet.getString("username"));
                users.setPassword(resultSet.getString("password"));
                users.setLoginIp(resultSet.getString("loginip"));
                users.setLoginTime(resultSet.getDate("logintime"));
                users.setCreateTime(resultSet.getDate("createtime"));
                users.setUserPhone(resultSet.getString("phone"));
                users.setNumber(resultSet.getString("number"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return users;
    }

    @Override
    public int[] getConsoleDataCourer() {
        int[] result = null;
        try {
            resultSet = super.query(SQL_CONSOLE_COURER, null);
            if (resultSet.next()) {
                result = new int[2];
                result[0] = resultSet.getInt(1);
                result[1] = resultSet.getInt(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }

        return result;
    }

    @Override
    public int[] getConsoleDataUser() {
        int[] result = null;
        try {
            resultSet = super.query(SQL_CONSOLE_USER, null);
            if (resultSet.next()) {
                result = new int[2];
                result[0] = resultSet.getInt(1);
                result[1] = resultSet.getInt(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }

        return result;
    }

    @Override
    public int delete(int id) {
        List<Integer> params = new ArrayList<>();
        params.add(id);
        int result = 0;
        try {
            result = super.update(SQL_DELETE, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }

    @Override
    public List<Users> getAllUsers(boolean isLimit, int offset, int pageNum,int identity) {
        List<Users> list = new ArrayList<>();
        List<Integer> params = new ArrayList<>();
        params.add(identity);

        if (!isLimit) { // 不需要分页
            resultSet = super.query(SQL_SELECT_COURER, null);
        } else { // 需要进行分页
            params.add(offset);
            params.add(pageNum);
            resultSet = super.query(SQL_SELECT_LIMIT_COURER, params);
        }
        try {
            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("id"));
                users.setUserName(resultSet.getString("username"));
                users.setPassword(resultSet.getString("password"));
                users.setLoginIp(resultSet.getString("loginip"));
                users.setLoginTime(resultSet.getDate("logintime"));
                users.setCreateTime(resultSet.getDate("createtime"));
                users.setUserPhone(resultSet.getString("phone"));
                users.setNumber(resultSet.getString("number"));
                users.setTotal(resultSet.getInt(9));
                list.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return list;
    }
}
