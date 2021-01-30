package com.wangzhen.algorithm.Interview.cainiao;



/**
 * 有两个线程，线程1只能输出数字， 线程2只能输出字母，顺序打印一副牌
 * 12345678910JQK
 * 12345678910JQK
 * 12345678910JQK
 * 12345678910JQK
 * xX
 */

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * Datetime:    2021/1/14   16:26
 * Author:   王震
 */

public class ThreadPrint {

    @Test
    public void test1() {
        ReentrantLock lock = new ReentrantLock();
        Condition numCondition = lock.newCondition();
        Condition charCondition = lock.newCondition();

            Thread charThread = new Thread(() -> {
                lock.lock();
                for (int i = 0; i < 4 ; i++) {
                    try {
                        numCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("12345678910");
                    charCondition.signal();
                }


            });
            Thread numThread = new Thread(() -> {
                for (int i = 0; i < 4; i++) {
                    try {
                        charCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("JQK");
                    numCondition.signal();
                }
                System.out.println("xX");
            });
            numThread.start();
            charThread.start();
            numCondition.signal();


    }
}