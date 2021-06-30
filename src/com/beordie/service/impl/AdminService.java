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

    /**
     * @description 获取当前登录管理员的姓名
     * @author 30500
     * @date 2021/6/30 9:46
     * @type [java.lang.String, java.lang.String]
     * @return com.beordie.model.Admin
     */
    @Override
    public Admin getByNamePass(String name, String password) {
        return adminDao.getByNamePass(name, password);
    }

    /**
     * @description 获取当前管理员的电话信息
     * @author 30500
     * @date 2021/6/30 9:47
     * @type [int, java.lang.String, java.util.Date]
     * @return int
     */
    @Override
    public int updateIpLoginTime(int id, String ip, Date time) {
        return adminDao.updateIpLoginTime(id, ip, time);
    }
}
