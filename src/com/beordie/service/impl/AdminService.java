package com.beordie.service.impl;

import com.beordie.dao.IAdminDao;
import com.beordie.dao.impl.AdminDaoImpl;
import com.beordie.model.Admin;
import com.beordie.service.IAdminService;

import java.util.Date;

/**
 * @Classname AdminService
 * @Description
 * @Date 2021/6/29 10:53
 * @Created 30500
 */
public class AdminService implements IAdminService {
    IAdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin getByNamePass(String name, String password) {
        return adminDao.getByNamePass(name, password);
    }

    @Override
    public int updateIpLoginTime(int id, String ip, Date time) {
        return adminDao.updateIpLoginTime(id, ip, time);
    }
}
