package com.beordie.mvc;

import java.lang.annotation.*;

/**
 * @Classname ResponseView
 * @Description 返回页面结果
 * @Date 2021/6/24 12:20
 * @Created 30500
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseView {
    public String value();
}
