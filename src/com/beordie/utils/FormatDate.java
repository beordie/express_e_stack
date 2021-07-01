package com.beordie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Classname FormatDate
 * @Description 格式化时间
 * @Date 2021/6/25 12:30
 * @Created 30500
 */
public class FormatDate {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @description 格式化时间
     * @author 30500
     * @date 2021/6/25 12:36
     * @type [java.util.Date]
     * @return java.lang.String
     */
    public static String parseDate(Date date) {
        return format.format(date);
    }

    /**
     * @description 解析字符串为时间戳
     * @author 30500
     * @date 2021/7/1 9:56
     * @type [java.lang.String]
     * @return long
     */
    public static long parseString(String time) {
        long temp = 0;
        try {
            temp = format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
