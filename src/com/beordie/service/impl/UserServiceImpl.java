package com.beordie.service.impl;

import com.beordie.dao.IUserDao;
import com.beordie.dao.impl.UserDaoImpl;
import com.beordie.model.Users;
import com.beordie.service.IUserService;

import java.util.Date;

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
}
