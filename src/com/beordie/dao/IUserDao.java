package com.beordie.dao;

import com.beordie.model.Users;

import java.util.Date;

/**
 * @Classname IUserDao
 * @Description 管理员登陆
 * @Date 2021/6/25 16:19
 * @Created 30500
 */
public interface IUserDao {
    // 根据登录名和密码查询数据库信息
    String SQL_FIND_NAME_PASSWORD = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
    // 根据管理 id 更新登陆ip和时间
    String SQL_UPDATE_IP_LOGINTIME = "UPDATE USERS SET LOGINIP=?, LOGINTIME=? WHERE ID=?";

    /**
     * @description 根据登录名和密码查询
     * @author 30500
     * @date 2021/6/25 16:21
     * @type []
     * @return com.beordie.model.Users
     */
    public Users getByNamePass(String name, String password);

    /**
     * @description 根据管理 id 更新登陆ip和时间
     * @author 30500
     * @date 2021/6/25 16:32
     * @type [int, java.lang.String, java.util.Date]
     * @return int
     */
    public int updateIpLoginTime(int id, String ip, Date time);
}
