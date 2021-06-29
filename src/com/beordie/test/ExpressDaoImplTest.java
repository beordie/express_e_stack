package com.beordie.test;

import com.beordie.dao.IExpressDao;
import com.beordie.dao.IUserDao;
import com.beordie.dao.impl.ExpressDaoImpl;
import com.beordie.dao.impl.UserDaoImpl;
import com.beordie.exception.RepeatCodeException;
import com.beordie.model.*;
import com.beordie.service.IExpressService;
import com.beordie.service.IUserService;
import com.beordie.service.impl.ExpressService;
import com.beordie.service.impl.UserServiceImpl;
import com.beordie.utils.JsonUtils;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Classname ExpressDaoImplTest
 * @Description TODO
 * @Date 2021/6/27 17:21
 * @Created 30500
 */
public class ExpressDaoImplTest {
    private IExpressDao dao = new ExpressDaoImpl();
    IExpressService service = new ExpressService();
    private IUserService userService = new UserServiceImpl();

    @Test
    public void getConsoleData() {
        int[] result = dao.getConsoleData();
        for (int i:result)
            System.out.println(i);
    }

    @Test
    public void getAllExpress() {
        List<Express> result = dao.getAllExpress(false,0,5);
        for (Express express:result)
            System.out.println(express);
    }

    @Test
    public void getByCode() {
        Express express = dao.getByCode("1000");
        System.out.println(express);
    }

    @Test
    public void getByNUmber() {
        Express express = dao.getByNUmber("1000");
        System.out.println(express);
    }

    @Test
    public void getBySysPhone() {
        List<Express> result = dao.getBySysPhone("13298992506");
        System.out.println(result.size());
    }

    @Test
    public void getByUserPhone() {
        List<Express> result = dao.getByUserPhone("13298992506");
        System.out.println(result.size());
    }

    @Test
    public void insertExpress() {
        Express express = new Express();
        express.setSysPhone("13298992506");
        express.setUserName("张三");
        express.setUserPhone("15934885591");
        express.setCompany("顺丰");
        express.setNumber("1101");
        express.setCode("1000");
        try {
            dao.insertExpress(express);
        } catch (RepeatCodeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateExpress() {
        Express express = new Express();
        express.setStatus(1);
        express.setUserName("李四");
        express.setUserPhone("15934885591");
        express.setCompany("顺丰");
        express.setNumber("1");
        int i = dao.updateExpress(1, express);
        System.out.println(i);
    }

    @Test
    public void deleteExpress() {
        int i = dao.deleteExpress(1);
        System.out.println(i);
    }

    @Test
    public void pickUpExpress() {
        int i = dao.pickUpExpress("1002");
        System.out.println(i);
    }

    @Test
    public void demo() {
//        List<Map<String, Integer>> result = service.getConsoleData();
//        Message message = new Message();
//        if (result != null) {
//            message.setData(result);
//            message.setResult("查询成功");
//            message.setStatus(0);
//        } else {
//            message.setResult("查询失败");
//            message.setStatus(-1);
//        }
//        String json = JsonUtils.parseObject(message);
        //3.    进行查询
//        List<StandardExpress> list = service.getAllExpress(true, 0, 5);
//        List<Map<String, Integer>> result = service.getConsoleData();
//        Integer total = result.get(0).get("data1_size");
//
//        //4.    将集合封装为 bootstrap-table识别的格式
//        ResultData<StandardExpress> data = new ResultData<>();
//        data.setRows(list);
//        data.setTotal(total);
//        String json = JsonUtils.parseObject(data);
//        System.out.println(json);
            List<StandardUsers> list = userService.getAllUsers(true,0,5,0);
    }
}