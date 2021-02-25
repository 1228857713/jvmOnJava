package com.wangzhen.javastudy.jvmTest.other;

/**
 * Description: 对象初始化与类初始化相关内容
 * Datetime:    2021/2/22   下午4:32
 * Author:   王震
 */
public class CLassInit {
    {
        int a = 1;
    }
    static {
        int b = 2;
    }
    int c =3;
    static  int d =4;
    static final int e =5;
    Object object= new Object();
    static Object object2 = new Object();
}
