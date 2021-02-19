package com.wangzhen.concurrent.juc.aqs;

import checkers.units.quals.C;
import com.wangzhen.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description: 测试 CyclicBarrier
 * Datetime:    2020/12/15   9:55
 * Author:   王震
 * desc: CyclicBarrier 让一组线程达到一个屏障时被阻塞，直到最后一个线程达到屏障时，屏障才会开门 所有被屏障拦截的线程才会继续干活
 *       一般用于，将一个任务分解为多个任务处理完成后，在合并处理的情形
 */
@Slf4j
public class TestCyclicBarrier {



    @Test
    public void test1() throws IOException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            log.info("{}人都到了，开始开会",5);
        });
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    SleepUtils.second(new Random().nextInt(5));
                    log.info("{}到会了", finalI);
                    cyclicBarrier.await();
                    log.info("收到，开会了");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.in.read();
    }
}