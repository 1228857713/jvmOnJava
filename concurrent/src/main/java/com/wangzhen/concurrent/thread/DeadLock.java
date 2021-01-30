package com.wangzhen.concurrent.thread;


import com.wangzhen.concurrent.util.SleepUtils;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * Datetime:    2021/1/13   下午9:39
 * Author:   王震
 */
@Slf4j
public class DeadLock {

    @Test
    public void test() throws IOException {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread thread = new Thread(() -> {
            synchronized (o1){
                SleepUtils.second(1);
                synchronized (o2){
                    log.info("线程1执行完毕");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (o2){
                SleepUtils.second(1);
                synchronized (o1){
                    log.info("线程2执行完毕");
                }
            }
        });
        thread.start();
        thread2.start();
        System.in.read();

    }
}
