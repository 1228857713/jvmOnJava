package com.wangzhen.algorithm.Interview.cainiao;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有两个线程，线程1只能输出数字， 线程2只能输出字母，顺序打印一副牌
 * 12345678910JQK
 * 12345678910JQK
 * 12345678910JQK
 * 12345678910JQK
 * xX
 */
public class ThreadPrint3 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition numCondition = lock.newCondition();
        Condition charCondition = lock.newCondition();

            Thread numThread = new Thread(() -> {
                lock.lock();
                for (int i = 0; i < 4; i++) {
                    try {
                        numCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("12345678910");
                    charCondition.signal();
                }
                lock.unlock();
            },"numThread");

            Thread  charThread= new Thread(() -> {
                lock.lock();
                for (int i = 0; i < 4; i++) {
                    try {
                        charCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("JQK");
                    numCondition.signal();
                }
                System.out.printf("xX");
                lock.unlock();
            },"");
            numThread.start();
            charThread.start();
            Thread.sleep(1);
            lock.lock();
            numCondition.signal();
            lock.unlock();
    }

}
