package com.wangzhen.concurrent.ThreadPool.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 线程池相关的测试类
 * Datetime:    2020/12/2   下午8:07
 * Author:   王震
 */
@Slf4j
public class ThreadPoolTest {

    @Test
    public void test1(){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //new ThreadPoolExecutor();
    }
}
