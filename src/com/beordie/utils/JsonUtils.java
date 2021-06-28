package com.beordie.utils;

import com.google.gson.Gson;

/**
 * @Classname JsonUtils
 * @Description 对象转换 json 字符串的工具类
 * @Date 2021/6/25 12:27
 * @Created 30500
 */
public class JsonUtils {
    private static Gson parse = new Gson();

    /**
     * @description 返回解析过的对象 json 字符串
     * @author 30500
     * @date 2021/6/25 12:29
     * @type [java.lang.Object]
     * @return java.lang.String
     */
    public static String parseObject(Object obj) {
        return parse.toJson(obj);
    }
}
