package com.beordie.service;

import com.beordie.dao.IExpressDao;
import com.beordie.exception.RepeatCodeException;
import com.beordie.model.Express;
import com.beordie.model.Message;
import com.beordie.model.StandardExpress;

import java.util.List;
import java.util.Map;

/**
 * @Classname IExpressService
 * @Description TODO
 * @Date 2021/6/26 22:24
 * @Created 30500
 */
public interface IExpressService {
    /**
     * @description 获取控制台的数据
     * @author 30500
     * @date 2021/6/26 22:26
     * @type [] 
     * @return com.beordie.model.Message
     */
    public List<Map<String, Integer>> getConsoleData();


    public List<StandardExpress> getAllExpress(boolean isLimit, int offset, int pageNum);


    public Express getByCode(String code);


    public Express getByNUmber(String number);


    public List<Express> getBySysPhone(String sysPhone);


    public List<StandardExpress> getByUserPhone(String userPhone);


    public int insertExpress(String number, String username, String company, String userPhone, String sysPhone) ;


    public int updateExpress(int id, String number, String username, String company, String userPhone, int status);


    public int deleteExpress(int id);

    public int pickUpExpress(String code);
}
