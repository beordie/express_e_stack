package com.beordie.service;

import com.beordie.model.Admin;

import java.util.Date;

/**
 * @Classname IAdminService
 * @Description 管理员
 * @Date 2021/6/29 10:53
 * @Created 30500
 */
public interface IAdminService {
    public Admin getByNamePass(String name, String password);

    public int updateIpLoginTime(int id, String ip, Date time);
}
