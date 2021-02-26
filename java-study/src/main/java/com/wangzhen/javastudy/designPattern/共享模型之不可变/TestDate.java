package com.wangzhen.javastudy.designPattern.共享模型之不可变;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Description:
 * Datetime:    2020/10/20   16:52
 * Author:   王震
 */
public class TestDate {

    @Test
    public void test01(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    System.out.println(sdf.parse("2018-12-20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}