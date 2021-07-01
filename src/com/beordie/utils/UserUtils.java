package com.beordie.utils;

import com.beordie.model.Users;
import com.beordie.wx.UserController;

import javax.servlet.http.HttpSession;

/**
 * @Classname UserUtils
 * @Description TODO
 * @Date 2021/6/30 22:27
 * @Created 30500
 */
public class UserUtils {
    public static void setTempInfo(HttpSession session, String userPhone, String cellCode) {
        session.setAttribute(userPhone, cellCode);
    }

    public static String getTempInfo(HttpSession session, String userPhone) {
        return (String) session.getAttribute(userPhone);
    }

    public static void setUserInfo(HttpSession session, Users users) {
        session.setAttribute("user", users);
    }

    public static Users getUserInfo(HttpSession session) {
        return (Users) session.getAttribute("user");
    }

    public static void logout(HttpSession session) {
        session.invalidate();
    }

    public static void setAuthority(HttpSession session, boolean authority) {
        session.setAttribute("authority", authority);
    }

    public static boolean getAuthority(HttpSession session) {
        return (boolean) session.getAttribute("authority");
    }

}
