package com.beordie.utils;

import javax.servlet.http.HttpSession;

/**
 * @Classname AdminUtils
 * @Description TODO
 * @Date 2021/6/27 23:04
 * @Created 30500
 */
public class AdminUtils {
    public static String getAdminName(HttpSession session) {
        return String.valueOf(session.getAttribute("userName"));
    }

    public static String getAdminPhone(HttpSession session) {
//        return String.valueOf(session.getAttribute("userPhone"));
        return "13298992506";
    }
}
