package com.beordie.dao;

import com.beordie.model.Admin;
import com.beordie.model.Users;

import java.util.Date;

/**
 * @Classname IAdminDao
 * @Description 管理员
 * @Date 2021/6/29 10:43
 * @Created 30500
 */
public interface IAdminDao {
    // 根据登录名和密码查询数据库信息
    String SQL_FIND_NAME_PASSWORD = "SELECT * FROM ADMIN WHERE USERNAME=? AND PASSWORD=?";
    // 根据管理 id 更新登陆ip和时间
    String SQL_UPDATE_IP_LOGINTIME = "UPDATE ADMIN SET LOGINIP=?, LOGINTIME=? WHERE ID=?";

    public Admin getByNamePass(String name, String password);

    public int updateIpLoginTime(int id, String ip, Date time);
}
