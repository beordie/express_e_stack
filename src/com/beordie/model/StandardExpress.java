package com.beordie.model;

import com.beordie.utils.FormatDate;

import java.util.Date;

/**
 * @Classname StandardExpress
 * @Description TODO
 * @Date 2021/6/27 19:46
 * @Created 30500
 */
public class StandardExpress {
    private Integer id;
    private String number;
    private String userName;
    private String userPhone;
    private String company;
    private String code;
    private String inTime;
    private String outTime;
    private Integer status;

    public StandardExpress(Express express) {
        this.id = express.getId();
        this.number = express.getNumber();
        this.userName = express.getUserName();
        this.userPhone = express.getUserPhone();
        this.company = express.getCompany();
        this.code = express.getCode() == null ? "已出库" : express.getCode();
        this.inTime = FormatDate.parseDate(express.getInTime());
        this.outTime = express.getOutTime() == null ? "未出库" : FormatDate.parseDate(express.getOutTime());
        this.status = express.getStatus();
        this.sysPhone = express.getSysPhone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }

    private String sysPhone;


}
