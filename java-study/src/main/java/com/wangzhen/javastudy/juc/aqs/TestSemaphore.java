package com.wangzhen.javastudy.juc.aqs;

import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * @desc: Semaphore 测试类
 *        Semaphore 维护一个信号量，保证最多只有几个线程可以同时执行
 *
 */
@Slf4j
public class TestSemaphore {


    @Test
    public void test1()  {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                try {
                    // 获得许可
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    log.info("running....");
                    SleepUtils.second(1);
                    log.info("ending....");
                }finally {
                    semaphore.release();
                }
            }).start();
        }
        SleepUtils.second(10);
    }
}
