package com.wangzhen.concurrent.juc.Lock;


import com.wangzhen.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * Datetime:    2020/12/13   下午9:12
 * Author:   王震
 */
@Slf4j
public class TestReentraLock {
    ReentrantLock lock = new ReentrantLock();


    public void fn(){
        try{
            lock.lock();
            SleepUtils.second(10);
        }catch (Exception e){
            lock.unlock();
        }
    }

    // 测试线程的状态
    // 结果表明 在juc 中获取锁失败后，锁会进入到 waiting 状态，这和 park 后的状态是一致的，没毛病
    @Test
    public void test1(){
        Thread thread = new Thread(() -> {
            fn();
        });
        Thread thread2 = new Thread(() -> {
            fn();
        });
        thread.start();
        thread2.start();
        log.info("thread 的状态"+thread.getState());
        log.info("thread2 的状态"+thread2.getState());
    }

    @Test
    public void test2(){
        Object o = new Object();
        Object o2= o;
        o2=null;
        System.out.println(o.toString());
        System.out.println(o2.toString());
    }


    public void testLock(){
        lock.lock();
        try {
            SleepUtils.second(10);
            System.out.println(Thread.currentThread().getName());
        }finally {
            lock.unlock();
        }
    }

    @Test
    public void test3() throws IOException {
        Thread thread = new Thread(() -> {
            testLock();
        });
        Thread thread2 = new Thread(() -> {
            testLock();
        });
        thread.start();
        thread2.start();
        System.in.read();
    }

}
