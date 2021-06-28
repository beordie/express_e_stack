package com.beordie.dao.impl;

import com.beordie.dao.IExpressDao;
import com.beordie.exception.RepeatCodeException;
import com.beordie.model.Express;
import com.beordie.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ExpressDaoImpl
 * @Description TODO
 * @Date 2021/6/26 22:17
 * @Created 30500
 */
public class ExpressDaoImpl extends DBUtils implements IExpressDao {
    @Override
    public int[] getConsoleData() {
        resultSet = super.query(SQL_CONSOLE, null);
        int[] data = null;
        try {
            if (resultSet.next()) {
                data = new int[4];
                data[0] = resultSet.getInt(1);
                data[1] = resultSet.getInt(2);
                data[2] = resultSet.getInt(3);
                data[3] = resultSet.getInt(4);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return data;
    }

    @Override
    public List<Express> getAllExpress(boolean isLimit, int offset, int pageNum) {
        List<Express> list = new ArrayList<>();
        if (!isLimit) {// 不需要分页
            resultSet = super.query(SQL_FIND_ALL, null);
        } else {// 需要进行分页
            List<Integer> params = new ArrayList<>();
            params.add(offset);
            params.add(pageNum);
            resultSet = super.query(SQL_FIND_LIMIT, params);
        }
        try {
            while (resultSet.next()) {
                Express e = new Express();
                e.setId(resultSet.getInt("id"));
                e.setNumber(resultSet.getString("number"));
                e.setUserName(resultSet.getString("username"));
                e.setUserPhone(resultSet.getString("userphone"));
                e.setCompany(resultSet.getString("company"));
                e.setCode(resultSet.getString("code"));
                e.setInTime(resultSet.getDate("intime"));
                e.setOutTime(resultSet.getDate("outtime"));
                e.setStatus(resultSet.getInt("status"));
                e.setSysPhone(resultSet.getString("sysphone"));

                list.add(e);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return list;
    }

    @Override
    public Express getByCode(String code) {
        List<String> params = new ArrayList<>();
        Express result = null;
        params.add(code);
        resultSet = super.query(SQL_FIND_BY_CODE, params);
        try {
            if (resultSet.next()) {
                result = new Express();
                result.setId(resultSet.getInt("id"));
                result.setNumber(resultSet.getString("number"));
                result.setUserName(resultSet.getString("username"));
                result.setUserPhone(resultSet.getString("userphone"));
                result.setCompany(resultSet.getString("company"));
                result.setCode(code);
                result.setInTime(resultSet.getDate("intime"));
                result.setOutTime(resultSet.getDate("outtime"));
                result.setStatus(resultSet.getInt("status"));
                result.setSysPhone(resultSet.getString("sysphone"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }

    @Override
    public Express getByNUmber(String number) {
        List<String> params = new ArrayList<>();
        Express result = null;
        params.add(number);
        resultSet = super.query(SQL_FIND_BY_NUMBER, params);
        try {
            if (resultSet.next()) {
                result = new Express();
                result.setId(resultSet.getInt("id"));
                result.setNumber(number);
                result.setUserName(resultSet.getString("username"));
                result.setUserPhone(resultSet.getString("userphone"));
                result.setCompany(resultSet.getString("company"));
                result.setCode(resultSet.getString("code"));
                result.setInTime(resultSet.getDate("intime"));
                result.setOutTime(resultSet.getDate("outtime"));
                result.setStatus(resultSet.getInt("status"));
                result.setSysPhone(resultSet.getString("sysphone"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }

    @Override
    public List<Express> getBySysPhone(String sysPhone) {
        List<Express> list = new ArrayList<>();
        List<String> params = new ArrayList<>();
        params.add(sysPhone);
        resultSet = super.query(SQL_FIND_BY_SYSPHONE, params);

        try {
            while (resultSet.next()) {
                Express e = new Express();
                e.setId(resultSet.getInt("id"));
                e.setNumber(resultSet.getString("number"));
                e.setUserName(resultSet.getString("username"));
                e.setUserPhone(resultSet.getString("userphone"));
                e.setCompany(resultSet.getString("company"));
                e.setCode(resultSet.getString("code"));
                e.setInTime(resultSet.getDate("intime"));
                e.setOutTime(resultSet.getDate("outtime"));
                e.setStatus(resultSet.getInt("status"));
                e.setSysPhone(sysPhone);

                list.add(e);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return list;
    }

    @Override
    public List<Express> getByUserPhone(String userPhone) {
        List<Express> list = new ArrayList<>();
        List<String> params = new ArrayList<>();
        params.add(userPhone);
        resultSet = super.query(SQL_FIND_BY_USERPHONE, params);

        try {
            while (resultSet.next()) {
                Express e = new Express();
                e.setId(resultSet.getInt("id"));
                e.setNumber(resultSet.getString("number"));
                e.setUserName(resultSet.getString("username"));
                e.setUserPhone(userPhone);
                e.setCompany(resultSet.getString("company"));
                e.setCode(resultSet.getString("code"));
                e.setInTime(resultSet.getDate("intime"));
                e.setOutTime(resultSet.getDate("outtime"));
                e.setStatus(resultSet.getInt("status"));
                e.setSysPhone(resultSet.getString("sysphone"));

                list.add(e);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            super.close();
        }
        return list;
    }

    @Override
    public int insertExpress(Express express) throws RepeatCodeException {
        List<Object> params = new ArrayList<>();
        params.add(express.getNumber());
        params.add(express.getUserName());
        params.add(express.getUserPhone());
        params.add(express.getCompany());
        params.add(express.getCode());
        params.add(express.getSysPhone());
        int result = 0;
        try {
            result = super.update(SQL_INSERT, params);
        } catch (Exception e) {
            // 取件码可能会重复
            if (e.getMessage().endsWith("for key 'number'")) {
                throw new RepeatCodeException("取件码重复");
            }
            // 其他错误由 JVM 自行处理
        } finally {
            super.close();
        }
        return result;
    }

    @Override
    public int updateExpress(int id, Express express) {
        // 配置参数
        List<Object> params = new ArrayList<>();
        params.add(express.getNumber());
        params.add(express.getUserName());
        params.add(express.getCompany());
        params.add(express.getStatus());
        params.add(id);
        int result = 0;
        try {
            result = super.update(SQL_UPDATE, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.close();
        }
        return result;
    }

    @Override
    public int deleteExpress(int id) {
        List<Integer> params = new ArrayList<>();
        params.add(id);
        int result = super.update(SQL_DELETE, params);
        return result;
    }

    @Override
    public int pickUpExpress(String code) {
        List<String> params = new ArrayList<>();
        params.add(code);
        int result = super.update(SQL_PICKUP, params);
        return result;
    }
}
