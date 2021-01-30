package com.wangzhen.concurrent.juc.test;

import com.wangzhen.concurrent.ThreadPool.threadPool.myself.ThreadPool;
import com.wangzhen.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * Description:
 * Datetime:    2020/12/13   下午7:44
 * Author:   王震
 */
@Slf4j
public class TestLock {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try{
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    System.out.println("hello world");
                }

            }catch (Exception e){
                System.out.println(e.toString());
            }

        });
        thread.start();
        System.out.println(thread.getState());
        SleepUtils.second(5);
        thread.interrupt();
        System.out.println(thread.getState());
    }

    @Test
    public void test1(){
        Thread thread = new Thread(()->{
//            SleepUtils.second(1);
            log.info("thread的状态为"+Thread.currentThread().getState());
            LockSupport.park();
            log.info("thread的状态为"+Thread.currentThread().getState());
        });
        thread.start();
        SleepUtils.second(1);
        //LockSupport.unpark(thread);
        System.out.println(thread.getState());

    }

    Object o = new Object();
    @Test
    public void test3() throws IOException {
        Thread thread = new Thread(() -> {
            sayHello();
        });
        Thread thread2 = new Thread(() -> {
            sayHello();
        });
        thread.start();
        thread2.start();
        log.info(thread.getState().toString());
        log.info(thread2.getState().toString());

        System.in.read();
    }

    public void sayHello(){
        synchronized (o){
            while (true){
                SleepUtils.second(1);
                System.out.println("hello world");

            }
        }
    }
}

