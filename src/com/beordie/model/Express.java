package com.beordie.model;

import java.util.Date;

/**
 * @Classname Express
 * @Description 快递
 * @Date 2021/6/25 16:00
 * @Created 30500
 */
public class Express {
    @Override
    public String toString() {
        return "Express{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", company='" + company + '\'' +
                ", code='" + code + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", status=" + status +
                ", sysPhone='" + sysPhone + '\'' +
                '}';
    }

    private Integer id;
    private String number;
    private String userName;
    private String userPhone;
    private String company;
    private String code;
    private Date inTime;
    private Date outTime;
    private Integer status;
    private String sysPhone;

    public Express() {
    }

    public Express(String number, String userName, String userPhone, String company, int status) {
        this.number = number;
        this.userName = userName;
        this.userPhone = userPhone;
        this.company = company;
        this.status = status;
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

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
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
}
