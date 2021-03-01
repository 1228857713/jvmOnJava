package com.wangzhen.javastudy.juc.aqs;

import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 测试 CountDownLatch
 * Datetime:    2020/12/15   10:25
 * Author:   王震
 * @desc: 多个线程执行完毕后执行countDown，最后开始执行await()对应线程
 */
@Slf4j
public class TestCountDownLatch {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                log.info("开始干活");
                SleepUtils.second(1);
                log.info("干活结束");
                latch.countDown();
            }).start();
        }
        log.info("start");
        latch.await();
        log.info("end");
    }


    /**
     * 模拟英雄联盟游戏，当所有角色都100% 后开始游戏
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService service = Executors.newFixedThreadPool(10);
        String [] arrays = new String[10];
        for (int j = 0; j < 10; j++) {
            int k =j;
            service.submit(()->{
                for (int i = 0; i <= 100; i++) {
                    arrays[k] = i+"%";
                    try {
                        Thread.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("\r"+ Arrays.toString(arrays));
                }
                latch.countDown();
            });

        }
        latch.await();
        System.out.println();
        System.out.println("游戏开始");
        service.shutdown();
    }
}