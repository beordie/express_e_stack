package com.beordie.dao;

import com.beordie.exception.RepeatCodeException;
import com.beordie.model.Express;

import java.util.List;

/**
 * @Classname IExpressDao
 * @Description 快递管理
 * @Date 2021/6/26 20:26
 * @Created 30500
 */
public interface IExpressDao {
    // 查询控制台需要数据
    String SQL_CONSOLE = "SELECT COUNT(ID) data1_size,COUNT(TO_DAYS(INTIME)=TO_DAYS(NOW()) OR NULL) data1_day,COUNT(STATUS=0 OR NULL) data2_size,COUNT(TO_DAYS(INTIME)=TO_DAYS(NOW()) AND STATUS=0 OR NULL) data2_day FROM EXPRESS";
    // 查询所有的快件信息
    String SQL_FIND_ALL = "SELECT * FROM EXPRESS";
    String SQL_FIND_LIMIT = "SELECT * FROM EXPRESS LIMIT ?,?";
    // 根据取件码查询快件信息
    String SQL_FIND_BY_CODE = "SELECT * FROM EXPRESS WHERE CODE = ?";
    // 根据快递编号查询快件信息
    String SQL_FIND_BY_NUMBER = "SELECT * FROM EXPRESS WHERE NUMBER = ?";
    // 根据管理员电话查询所有快件信息
    String SQL_FIND_BY_SYSPHONE = "SELECT * FROM EXPRESS WHERE SYSPHONE = ?";
    // 根据用户手机号查询所有快件信息
    String SQL_FIND_BY_USERPHONE = "SELECT * FROM EXPRESS WHERE USERPHONE = ?";
    // 根据用户手机号查询所有快件信息
    String SQL_FIND_BY_USERPHONE_AND_STATUS = "SELECT * FROM EXPRESS WHERE USERPHONE = ? AND STATUS=?";
    // 新增一条快件信息
    String SQL_INSERT = "INSERT INTO EXPRESS VALUES(NULL,?,?,?,?,?,NOW(),NULL,0,?)";
    // 更新快件信息
    String SQL_UPDATE = "UPDATE EXPRESS SET NUMBER=?,USERNAME=?,COMPANY=?,STATUS=? WHERE ID=?";
    // 删除快件
    String SQL_DELETE = "DELETE FROM EXPRESS WHERE ID = ?";
    // 取快件（更新快件的状态为 1 ）
    String SQL_PICKUP = "UPDATE EXPRESS SET STATUS = 1,CODE=NULL,OUTTIME=NOW() WHERE CODE = ?";

    /**
     * @description 获取控制台的数据
     * @author 30500
     * @date 2021/6/26 22:18
     * @type []
     * @return int[]
     */
    public int[] getConsoleData();
    /**
     * @description 获取全部快递信息 （可分页）
     * @author 30500
     * @date 2021/6/27 12:18
     * @type [boolean, int, int]
     * @return java.util.List<com.beordie.model.Express>
     */
    public List<Express> getAllExpress(boolean isLimit, int offset, int pageNum);
    /**
     * @description 根据取件码查询快递信息
     * @author 30500
     * @date 2021/6/27 12:18
     * @type [java.lang.String]
     * @return com.beordie.model.Express
     */
    public Express getByCode(String code);
    /**
     * @description 根据快递单号查询快递信息
     * @author 30500
     * @date 2021/6/27 12:18
     * @type [java.lang.String]
     * @return com.beordie.model.Express
     */
    public Express getByNUmber(String number);
    /**
     * @description 根据管理员电话查询快递信息
     * @author 30500
     * @date 2021/6/27 12:18
     * @type [java.lang.String]
     * @return java.util.List<com.beordie.model.Express>
     */
    public List<Express> getBySysPhone(String sysPhone);
    /**
     * @description 根据用户电话查询快递信息
     * @author 30500
     * @date 2021/6/27 12:19
     * @type [java.lang.String]
     * @return java.util.List<com.beordie.model.Express>
     */
    public List<Express> getByUserPhone(String userPhone);
    /**
     * @description 新增快递信息
     * @author 30500
     * @date 2021/6/27 12:19
     * @type [com.beordie.model.Express]
     * @return int
     */
    public int insertExpress(Express express) throws RepeatCodeException;
    /**
     * @description 更新快递信息
     * @author 30500
     * @date 2021/6/27 12:19
     * @type [com.beordie.model.Express]
     * @return int
     */
    public int updateExpress(int id, Express express);
    /**
     * @description 根据快递 id 删除快递
     * @author 30500
     * @date 2021/6/27 12:19
     * @type [int]
     * @return int
     */
    public int deleteExpress(int id);
    /**
     * @description 取件
     * @author 30500
     * @date 2021/6/27 12:20
     * @type [java.lang.String]
     * @return int
     */
    public int pickUpExpress(String code);

    /**
     * @description 根据电话和状态查询
     * @author 30500
     * @date 2021/7/1 15:42
     * @type [java.lang.String]
     * @return java.util.List<com.beordie.model.Express>
     */
    public List<Express> getByUserPhoneAndStatus(String userPhone, int status);
}
