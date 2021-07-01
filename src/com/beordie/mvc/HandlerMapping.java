package com.beordie.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Classname HanderMapping
 * @Description TODO
 * @Date 2021/6/24 12:41
 * @Created 30500
 */
public class HandlerMapping {
    public static Map<String, Mapping> data = new HashMap<>();

    public static Map<String, Mapping> getData() {
        return data;
    }

    /**
     * @description 加载配置文件信息
     * @author 30500
     * @date 2021/6/24 14:20
     * @type [java.io.InputStream]
     * @return void
     */
    public static void load(InputStream stream){
        // 转换读取流
        Properties config = new Properties();
        try {
            config.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取配置文件中的所有类
        Collection<Object> objects = config.values();
        // 配置响应方法
        for (Object obj:objects) {
            // 映射类
            String name = (String) obj;
            try {
                Class c = Class.forName(name);
                // 生成实例对象
                Object instance = c.getConstructor().newInstance();
                // 获取类的所有共有方法
                Method[] methods = c.getMethods();
                // 遍历方法进行填充工具
                for (Method method:methods) {
                    // 拿到方法上的所有注解
                    Annotation[] annotations = method.getAnnotations();
                    if (annotations != null) {
                        for (Annotation annotation:annotations) {
                            // 返回文本信息
                            if (annotation instanceof ResponseText) {
                                Mapping mapping = new Mapping(instance, ResponseType.TEXT, method);
                                Object re = data.put(((ResponseText) annotation).value(), mapping);
                                if (re != null)
                                    throw new RuntimeException("方法重复" + ((ResponseText) annotation).value());
                                // 返回页面信息
                            } else if (annotation instanceof ResponseView) {
                                Mapping mapping = new Mapping(instance, ResponseType.VIEW, method);
                                Object re = data.put(((ResponseView) annotation).value(), mapping);
                                if (re != null)
                                    throw new RuntimeException("方法重复" + ((ResponseView) annotation).value());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Mapping{
        // 响应对象
        private Object object;
        // 响应类型
        private ResponseType type;
        // 响应方法
        private Method method;

        public Mapping() {
        }

        public Mapping(Object object, ResponseType type, Method method) {
            this.object = object;
            this.type = type;
            this.method = method;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public ResponseType getType() {
            return type;
        }

        public void setType(ResponseType type) {
            this.type = type;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }
    }
}
