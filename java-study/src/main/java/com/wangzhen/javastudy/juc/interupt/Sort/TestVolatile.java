package com.wangzhen.javastudy.juc.interupt.Sort;

public class TestVolatile {
    public   static int i=0;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(i);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                i++;
            }
        }).start();

    }
}
