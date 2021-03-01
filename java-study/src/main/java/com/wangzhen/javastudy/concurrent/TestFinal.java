package com.wangzhen.javastudy.concurrent;

/**
 * Description: 测试final 修饰属性相关的代码
 * Datetime:    2021/2/26   下午6:15
 * Author:   王震
 */
public class TestFinal {
    int i;
    final int j=2;
    static TestFinal obj;

    public TestFinal() {
        i=1;

    }
    public static void write(){
        obj = new TestFinal();
    }
    public static void read(){
        TestFinal object = obj;
        int a = object.i;
        int b = object.j;
    }
}
