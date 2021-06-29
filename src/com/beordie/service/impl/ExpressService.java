package com.beordie.service.impl;

import com.beordie.dao.IExpressDao;
import com.beordie.dao.impl.ExpressDaoImpl;
import com.beordie.exception.RepeatCodeException;
import com.beordie.model.Express;
import com.beordie.model.Message;
import com.beordie.model.StandardExpress;
import com.beordie.service.IExpressService;
import com.beordie.utils.AdminUtils;
import com.beordie.utils.GenerationCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ExpressService
 * @Description TODO
 * @Date 2021/6/26 22:33
 * @Created 30500
 */
public class ExpressService implements IExpressService {
    private IExpressDao expressDao = new ExpressDaoImpl();

    @Override
    public List<Map<String, Integer>> getConsoleData() {
        int[] result = expressDao.getConsoleData();
        List<Map<String, Integer>> data = null;
        Message message = new Message();
        if (result == null) {
            return data;
        } else {
            data = new ArrayList<>();
            Map<String, Integer> data1 = new HashMap<>();
            Map<String, Integer> data2 = new HashMap<>();
            data1.put("data1_size", result[0]);
            data1.put("data1_day", result[1]);
            data2.put("data2_size", result[2]);
            data2.put("data2_day", result[3]);
            data.add(data1);
            data.add(data2);
        }
        return data;
    }

    @Override
    public List<StandardExpress> getAllExpress(boolean isLimit, int offset, int pageNum) {
        // 进行格式转换后返回
        List<Express> result = expressDao.getAllExpress(isLimit, offset, pageNum);
        List<StandardExpress> expressList = new ArrayList<>();
        for (Express e : result) {
            StandardExpress express = new StandardExpress(e);
            expressList.add(express);
        }
        return expressList;
    }

    @Override
    public Express getByCode(String code) {
        return null;
    }

    @Override
    public Express getByNUmber(String number) {
        return expressDao.getByNUmber(number);
    }

    @Override
    public List<Express> getBySysPhone(String sysPhone) {
        return null;
    }

    @Override
    public List<Express> getByUserPhone(String userPhone) {
        return null;
    }

    @Override
    public int insertExpress(String number, String username, String company, String userPhone, String sysPhone) {
        Express express = new Express();
        express.setNumber(number);
        express.setUserName(username);
        express.setUserPhone(userPhone);
        express.setCompany(company);
        express.setCode(GenerationCode.generationCode());
        express.setSysPhone(sysPhone);
        int result = 0;
        try {
            result = expressDao.insertExpress(express);
        } catch (RepeatCodeException e) {
            return insertExpress(number, username, company, userPhone,sysPhone);
        }
        return result;
    }

    @Override
    public int updateExpress(int id, String number, String username, String company, String userPhone, int status) {
        int result = 0;
        if (userPhone != null) { // 修改了用户的手机号、重新进行数据的删除和数据的插入
            result = expressDao.deleteExpress(id);
            if (result > 0) {
                result += insertExpress(number, username, company, userPhone, AdminUtils.getAdminPhone(null));
            }
        } else { // 正常的数据更新
            Express express = new Express(number, username, userPhone, company, status);
            result = expressDao.updateExpress(id, express);
        }
        return result;
    }

    @Override
    public int deleteExpress(int id) {
        return expressDao.deleteExpress(id);
    }

    @Override
    public int pickUpExpress(String code) {
        return 0;
    }
}
