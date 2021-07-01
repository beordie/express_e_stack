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
    public List<Map<String, String>> getAllRank() {
        return userDao.getAllRank();
    }

    @Override
    public int updateIpLoginTime(int id, String ip, Date time) {
        return userDao.updateIpLoginTime(id, ip, time);
    }

    @Override
    public Users getByPhone(String phone, int identity) {
        return userDao.getByPhone(phone, identity);
    }

    @Override
    public List<Map<String, Integer>> getConsoleData() {
        int[] resultC = userDao.getConsoleDataCourer();
        int[] resultU = userDao.getConsoleDataUser();

        List<Map<String, Integer>> result = null;
        if (resultC == null || resultU == null)
            return null;
        else {
            result = new ArrayList<>();
            Map<String, Integer> temp1 = new HashMap<>();
            Map<String, Integer> temp2 = new HashMap<>();

            temp1.put("data3_size", resultC[0]);
            temp1.put("data3_day", resultC[1]);
            temp2.put("data4_size", resultU[0]);
            temp2.put("data4_day", resultU[1]);

            result.add(temp1);
            result.add(temp2);
        }
        return result;
    }

    @Override
    public List<StandardUsers> getAllUsers(boolean isLimit, int offset, int pageNum, int identity) {
        List<Users> users = userDao.getAllUsers(isLimit, offset, pageNum, identity);
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
    public int insert(String username, String password, String phone, String number, int identity) {
        return userDao.insert(username, password, phone, number, identity);
    }

    @Override
    public int update(String username, String password, String phone, String number, int id) {
        return userDao.update(username, password, phone, number, id);
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }
}
