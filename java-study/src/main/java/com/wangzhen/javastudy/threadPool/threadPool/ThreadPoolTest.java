package com.wangzhen.javastudy.threadPool.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Description: 线程池相关的测试类
 * Datetime:    2020/12/2   下午8:07
 * Author:   王震
 */
@Slf4j
public class ThreadPoolTest {

    @Test
    public void test1() throws IOException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1000, 10000, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        boolean b = threadPoolExecutor.prestartCoreThread();
        System.in.read();


    }
}
