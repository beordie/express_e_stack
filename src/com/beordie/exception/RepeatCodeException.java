package com.beordie.exception;

/**
 * @Classname RepeatCodeException
 * @Description 取件码重复异常
 * @Date 2021/6/27 12:44
 * @Created 30500
 */
public class RepeatCodeException extends Exception{
    public RepeatCodeException() {
        super();
    }

    public RepeatCodeException(String message) {
        super(message);
    }
}
