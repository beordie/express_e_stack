package com.beordie.utils;

import javax.servlet.http.HttpSession;

/**
 * @Classname AdminUtils
 * @Description TODO
 * @Date 2021/6/27 23:04
 * @Created 30500
 */
public class AdminUtils {
    /**
     * @description 货物当前登录的管理员的姓名
     * @author 30500
     * @date 2021/6/30 9:49
     * @type [javax.servlet.http.HttpSession]
     * @return java.lang.String
     */
    public static String getAdminName(HttpSession session) {
        Object userName = session.getAttribute("userName");
        System.out.println(userName);
        if (userName == null)
            return null;
        return userName.toString();
    }

    /**
     * @description 获取当前登录系统的管理员的电话
     * @author 30500
     * @date 2021/6/30 9:50
     * @type [javax.servlet.http.HttpSession]
     * @return java.lang.String
     */
    public static String getAdminPhone(HttpSession session) {
//        return String.valueOf(session.getAttribute("userPhone"));
        return "13298992506";
    }

    /**
     * @description 退出登陆 session 失效
     * @author 30500
     * @date 2021/6/30 9:51
     * @type [javax.servlet.http.HttpSession]
     * @return void
     */
    public static void quit(HttpSession session) {
        session.invalidate();
    }
}
