package com.beordie.mvc;

import java.lang.annotation.*;

/**
 * @Classname ResponseText
 * @Description 以文字形式返回
 * @Date 2021/6/24 12:19
 * @Created 30500
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseText {
    public String value();
}
