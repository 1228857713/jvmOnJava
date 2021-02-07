package com.wangzhen.jvmTest.G1Demo;/**
 * Description:
 * Datetime:    2021/1/31   下午7:56
 * Author:   王震
 */

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @version 1.0
 * @author wangzhen
 * @date 2021/1/31 下午7:56
 */
public class TestReflect {
    String name;
    @Test
    public void test1() throws IllegalAccessException {
        TestReflect testReflect = new TestReflect();
        testReflect.name = "wangzhen";
        Field[] declaredFields = testReflect.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            if(declaredFields[i].getName().equals("name")){
                System.out.println(declaredFields[i].get(testReflect));
            }
        }

    }
}
