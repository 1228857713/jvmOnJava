package com.wangzhen.algorithm.DesignPattern.singleton;

import org.junit.Test;

/**
 * Description:
 * Datetime:    2021/1/18   下午2:43
 * Author:   王震
 */
public class App {
    @Test
    public void test1(){
        EnumClass instance = EnumClass.Instance;
        instance.sayName();
    }

    @Test
    public void test3(){
        Dcl instance = Dcl.getInstance();
        for (int i = 0; i < 1000; i++) {

        }

    }

    public static void main(String[] args) {

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello world");
            }
        },"thread2");
        thread2.start();
    }


}
