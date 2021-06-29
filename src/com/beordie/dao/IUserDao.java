package com.beordie.dao;

import com.beordie.model.Users;

import java.util.Date;
import java.util.List;

/**
 * @Classname IUserDao
 * @Description 管理员登陆
 * @Date 2021/6/25 16:19
 * @Created 30500
 */
public interface IUserDao {
    // 查询控制台需要数据
    String SQL_CONSOLE = "SELECT COUNT(ID) data_size,COUNT(TO_DAYS(CREATETIME)=TO_DAYS(NOW()) OR NULL) data_day FROM  USERS";
    // 根据登录名和密码查询数据库信息
    String SQL_FIND_NAME_PASSWORD = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
    // 根据管理 id 更新登陆ip和时间
    String SQL_UPDATE_IP_LOGINTIME = "UPDATE USERS SET LOGINIP=?, LOGINTIME=? WHERE ID=?";
    // 新增一条数据项
    String SQL_INSERT = "INSERT INTO USERS VALUES(NULL,?,?,NULL,NULL,NOW(),?,?)";
    // 删除一条记录
    String SQL_DELETE = "DELETE FROM USERS WHERE ID=?";
    // 跟新数据
    String SQL_UPDATE = "UPDATE USERS SET USERNAME=?,PHONE=?,PASSWORD=? WHERE ID=?";
    // 查询所有
    String SQL_SELECT = "SELECT * FROM USERS";
    // 分页查询
    String SQL_SELECT_LIMIT = "SELECT * FROM USERS LIMIT ?,?";
    // 根据电话查询
    String SQL_FIND_BY_PHONE = "SELECT * FROM USERS WHERE PHONE = ?";

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

    /**
     * @description 分页查询
     * @author 30500
     * @date 2021/6/28 17:26
     * @type [boolean, int, int]
     * @return java.util.List<com.beordie.model.Users>
     */
    public List<Users> getAllUsers(boolean isLimit, int offset, int pageNum);

    /**
     * @description 插入一条数据
     * @author 30500
     * @date 2021/6/28 18:56
     * @type [java.lang.String, java.lang.String, java.lang.String]
     * @return int
     */
    public int insert(String username, String password, String phone, String number);

    /**
     * @description 修改记录
     * @author 30500
     * @date 2021/6/28 18:57
     * @type [java.lang.String, java.lang.String, java.lang.String, int]
     * @return int
     */
    public int update(String username, String password, String phone, int id);

    /**
     * @description 删除一条记录
     * @author 30500
     * @date 2021/6/28 18:58
     * @type [int]
     * @return int
     */
    public int delete(int id);

    /**
     * @description 获取控制台信息
     * @author 30500
     * @date 2021/6/28 19:27
     * @type []
     * @return int[]
     */
    public int[] getConsoleData();

    /**
     * @description 根据电话查询
     * @author 30500
     * @date 2021/6/28 22:28
     * @type [java.lang.String]
     * @return com.beordie.model.Users
     */
    public Users getByPhone(String phone);
}
