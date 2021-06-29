package com.beordie.model;

import com.beordie.utils.FormatDate;

import java.util.Date;

/**
 * @Classname StandardUsers
 * @Description 
 * @Date 2021/6/28 19:11
 * @Created 30500
 */
public class StandardUsers {
    private Integer id;
    private String userName;
    private String password;
    private String loginIp;
    private String loginTime;
    private String createTime;
    private String userPhone;
    private String number;
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public StandardUsers(Users users) {
        this.id = users.getId();
        this.userName = users.getUserName();
        this.password = users.getPassword();
        this.loginIp = users.getLoginIp();
        this.loginTime = users.getLoginTime() == null ? "未登录" : FormatDate.parseDate(users.getLoginTime());
        this.createTime = FormatDate.parseDate(users.getCreateTime());
        this.userPhone = users.getUserPhone();
        this.number = users.getNumber();
        this.total = users.getTotal();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
