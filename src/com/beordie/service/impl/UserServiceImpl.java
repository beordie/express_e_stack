package com.beordie.service.impl;

import com.beordie.dao.IUserDao;
import com.beordie.dao.impl.UserDaoImpl;
import com.beordie.model.StandardUsers;
import com.beordie.model.Users;
import com.beordie.service.IUserService;

import java.util.*;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2021/6/25 16:38
 * @Created 30500
 */
public class UserServiceImpl implements IUserService {
    private IUserDao userDao = new UserDaoImpl();

    @Override
    public Users getByNamePass(String name, String password) {
        return userDao.getByNamePass(name, password);
    }

    @Override
    public int updateIpLoginTime(int id, String ip, Date time) {
        return userDao.updateIpLoginTime(id, ip, time);
    }

    @Override
    public Users getByPhone(String phone) {
        return userDao.getByPhone(phone);
    }

    @Override
    public Map<String, Integer> getConsoleData() {
        int[] result = userDao.getConsoleData();
        Map<String, Integer> temp = null;
        if (result == null)
            return null;
        else {
            temp = new HashMap<>();
            temp.put("data3_size", result[0]);
            temp.put("data3_day", result[1]);
        }
        return temp;
    }

    @Override
    public List<StandardUsers> getAllUsers(boolean isLimit, int offset, int pageNum) {
        List<Users> users = userDao.getAllUsers(isLimit, offset, pageNum);
        List<StandardUsers> list = new ArrayList<>();
        if (users != null) {
            for (Users u : users) {
                StandardUsers standardUsers = new StandardUsers(u);
                list.add(standardUsers);
            }
        }
        return list;
    }

    @Override
    public int insert(String username, String password, String phone, String number) {
        return userDao.insert(username, password, phone, number);
    }

    @Override
    public int update(String username, String password, String phone, int id) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
