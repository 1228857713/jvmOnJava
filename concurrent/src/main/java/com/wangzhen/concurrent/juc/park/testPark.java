package com.wangzhen.concurrent.juc.park;

import com.wangzhen.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;


@Slf4j
public class testPark {

    @Test
    public void test(){
        Thread t1 = new Thread(() -> {
            log.debug("start...");
            SleepUtils.second(2);
            log.debug("park....");
            LockSupport.park();
            log.debug("resume");
        });
        t1.start();
        SleepUtils.second(1);
        log.debug("unpark....");
        LockSupport.unpark(t1);
    }

    // 测试线程的打断
    @Test
    public void test2(){
        Thread thread = new Thread(() -> {
            try {
                fn();
            } catch (InterruptedException e) {
                log.info("捕获异常");
            }
        });
        thread.start();
        thread.interrupt();
        SleepUtils.second(5);
    }

    public void fn() throws InterruptedException {
            LockSupport.park(this);
            log.info("hello world");
    }

    // 测试线程的打断
    @Test
    public void test3(){
        Thread thread = new Thread(() -> {
            log.info("开始睡眠");
            SleepUtils.second(5);
            log.info("结束睡眠");


        });
        thread.start();
        thread.interrupt();
        SleepUtils.second(6);
    }
}
