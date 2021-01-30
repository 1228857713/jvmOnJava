package com.wangzhen.algorithm.Interview.cainiao;

import java.util.Arrays;

/**
 * 有两个线程，线程1只能输出数字， 线程2只能输出字母，顺序打印一副牌
 * 12345678910JQK
 * 12345678910JQK
 * 12345678910JQK
 * 12345678910JQK
 * xX
 */
public class Test3 {
    Object o = new Object();
    String[] strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "x", "X"};
    final Thread numberThread = new Thread(() -> Arrays.stream(strings).forEach(e -> {
        synchronized (o) {
            if (Character.isDigit(e.charAt(0))) {
                System.out.println(e);
            }
            try {
                o.notify();
                o.wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

        }
    }));

    Thread charThread = new Thread(() -> Arrays.stream(strings).forEach(e -> {
        synchronized (o) {
            if (!Character.isDigit(e.charAt(0))) {
                System.out.println(e);
            }
//            if ("X".equals(e)){
//                numberThread.stop();
//            }else{
            try {
                o.notify();
                o.wait();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
//            }

        }
    }));

    public static void main(String[] args) {
        Test3 test = new Test3();
        test.numberThread.start();
        test.charThread.start();
    }
}