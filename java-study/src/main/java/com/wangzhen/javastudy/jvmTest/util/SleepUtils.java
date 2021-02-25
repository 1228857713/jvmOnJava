package com.wangzhen.javastudy.jvmTest.util;

/**
 * Description:
 * Datetime:    2021/1/26   10:01
 * Author:   王震
 */
public class SleepUtils {
    public static void sleep(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}