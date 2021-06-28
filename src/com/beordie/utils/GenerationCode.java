package com.beordie.utils;

import java.util.Random;

/**
 * @Classname GenerationCode
 * @Description 生成六位数的随机取件码
 * @Date 2021/6/27 12:20
 * @Created 30500
 */
public class GenerationCode {
    private static Random random = new Random();

    public static String generationCode() {
        return random.nextInt(900000) + 100000 + "";
    }
}
