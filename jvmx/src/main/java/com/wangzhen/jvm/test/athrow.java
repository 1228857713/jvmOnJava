package com.wangzhen.jvm.test;

/**
 * Description:
 * Datetime:    2020/10/4   1:17 下午
 * Author:   王震
 */
public class athrow {
    public static void test() {
        int i = 100;
        try {
            func();
        } catch (NumberFormatException e) {
            i = 200;
        }
    }

    public static void func() {
        throw new IndexOutOfBoundsException();
    }
}
