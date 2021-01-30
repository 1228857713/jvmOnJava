package com.wangzhen.concurrent.thread;

import org.junit.Test;

import java.io.IOException;

/**
 * Description:
 * Datetime:    2021/1/22   9:25
 * Author:   王震
 */
public class TestThreadLocal {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    Thread [] threads;
    @Test
    public void test01() throws IOException {
        threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                threadLocal.set("thread"+ finalI);
                System.out.println(threadLocal.get());
            }, "thread" + i);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        System.in.read();

      //  System.out.printf(threadLocal.get());
    }


    @Test
    public void test02(){
        TestThreadLocal testThreadLocal = new TestThreadLocal();
        TestThreadLocal testThreadLocal2 = new TestThreadLocal();

    }
}