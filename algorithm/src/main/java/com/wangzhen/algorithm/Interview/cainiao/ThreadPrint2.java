package com.wangzhen.algorithm.Interview.cainiao;



/**
 * 有两个线程，线程1只能输出数字， 线程2只能输出字母，顺序打印一副牌
 * 12345678910JQK
 * 12345678910JQK
 * 12345678910JQK
 * 12345678910JQK
 * xX
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:  菜鸟面试题，交替打印
 * Datetime:    2021/1/14   16:26
 * Author:   王震
 */

@Slf4j
public class ThreadPrint2 {

    @Test
    public void test1() throws InterruptedException, IOException {
        Semaphore numSemaphore = new Semaphore(0);
        Semaphore charSemaphore = new Semaphore(0);
        Thread numThread = new Thread(() -> {
                for (int i = 0; i < 4 ; i++) {
                    try {
                        numSemaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("12345678910");
                    charSemaphore.release();
                }
            });
            Thread charThread = new Thread(() -> {

                for (int i = 0; i < 4; i++) {
                    try {
                        charSemaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("JQK");
                    numSemaphore.release();
                }
                log.info("xX");
            });
            numThread.start();
            charThread.start();
            Thread.sleep(10000);
           // numSemaphore.release();
            numThread.interrupt();
            System.in.read();

    }
}