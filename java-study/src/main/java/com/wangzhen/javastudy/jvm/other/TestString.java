package com.wangzhen.javastudy.jvm.other;


import org.junit.Test;

/**
 * Description:
 * Datetime:    2021/3/2   下午3:19
 * Author:   王震
 */
public class TestString {

    @Test
    public void test01() throws InterruptedException {
        String str = "";
        while (true){
           str+="1";
           str.intern();
        }
    }
}
