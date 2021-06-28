package com.beordie.service;

import com.beordie.model.Users;

import java.util.Date;

/**
 * @Classname IUserService
 * @Description 管理员
 * @Date 2021/6/25 16:30
 * @Created 30500
 */
public interface IUserService {
    /**
     * @description 根据登录名和密码查询
     * @author 30500
     * @date 2021/6/25 16:31
     * @type [java.lang.String, java.lang.String]
     * @return com.beordie.model.Users
     */
    public Users getByNamePass(String name, String password);

    /**
     * @description 根据管理 id 更新登陆ip和时间
     * @author 30500
     * @date 2021/6/25 16:37
     * @type [int, java.lang.String, java.util.Date]
     * @return int
     */
    public int updateIpLoginTime(int id, String ip, Date time);
}
