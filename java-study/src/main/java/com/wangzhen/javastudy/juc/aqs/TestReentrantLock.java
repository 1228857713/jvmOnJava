package com.wangzhen.javastudy.juc.aqs;

import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.util.concurrent.locks.*;


@Slf4j
public class TestReentrantLock {
    ReentrantLock lock = new ReentrantLock();

    @Test
    public void test1(){
        Thread thread = new Thread(()->{
            System.out.printf("100");
            LockSupport.park();
            SleepUtils.second(2);
            System.out.printf("1000");

        });
        thread.start();
        SleepUtils.second(1);
        System.out.println(thread.getState());
        LockSupport.unpark(thread);
        System.out.println(thread.getState());
        lock.lock();
        try{

        }finally {
            lock.unlock();
        }


    }

    @Test
    public void test2(){
        lock.lock();
        try {
            Condition condition = lock.newCondition();
            // wait/notify 方法是Object上的方法 所有的对象都有
            // await/signal 是 condition 上的方法，
            condition.await();
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
