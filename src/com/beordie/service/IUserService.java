package com.beordie.service;

import com.beordie.model.StandardUsers;
import com.beordie.model.Users;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * @description 分页查询
     * @author 30500
     * @date 2021/6/28 19:05
     * @type [boolean, int, int] 
     * @return java.util.List<com.beordie.model.Users>
     */
    public List<StandardUsers> getAllUsers(boolean isLimit, int offset, int pageNum, int identity);

    /**
     * @description 插入一条数据
     * @author 30500
     * @date 2021/6/28 19:08
     * @type [java.lang.String, java.lang.String, java.lang.String]
     * @return int
     */
    public int insert(String username, String password, String phone, String number, int identity);

    /**
     * @description 修改记录
     * @author 30500
     * @date 2021/6/28 19:08
     * @type [java.lang.String, java.lang.String, java.lang.String, int]
     * @return int
     */
    public int update(String username, String password, String phone, String number, int id);

    /**
     * @description 删除一条记录
     * @author 30500
     * @date 2021/6/28 19:09
     * @type [int]
     * @return int
     */
    public int delete(int id);

    /**
     * @description 获取控制台需要数据
     * @author 30500
     * @date 2021/6/28 19:31
     * @type []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Integer>>
     */
    public List<Map<String, Integer>> getConsoleData();

    /**
     * @description 根据电话查询
     * @author 30500
     * @date 2021/6/28 22:34
     * @type [java.lang.String]
     * @return com.beordie.model.Users
     */
    public Users getByPhone(String phone, int identity);
}
