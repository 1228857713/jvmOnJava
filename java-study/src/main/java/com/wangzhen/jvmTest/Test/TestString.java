package com.wangzhen.jvmTest.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description:
 * Datetime:    2021/1/26   9:39
 * Author:   王震
 */
@Slf4j
public class TestString {

    /**
     * 从test01可以看出 String 是存放在StringPool中(存放在堆中)是全局共享的
     */
    @Test
    public void test01(){
        String str1 = "str";
        String str2 = "str";
        // true
        System.out.println(String.valueOf(str1 == str2));

    }

    @Test
    public void test02(){
        int i1 = 10;
        int i2 = 10;
        // true
        System.out.printf(String.valueOf(i1==i2));
    }

    @Test
    public void test03(){
        Integer integer1 = 10;
        Integer integer2 = 10;
        // true
        System.out.println(String.valueOf(integer1==integer2));

        Integer integer3 = 1024;
        Integer integer4 = 1024;
        // false
        System.out.println(String.valueOf(integer3==integer4));
    }
}