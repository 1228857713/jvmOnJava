package com.wangzhen.javastudy.ThreadPool.ThreadPoolExecutor;

import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Description:
 * Datetime:    2020/12/9   下午7:07
 * Author:   王震
 */
@Slf4j
public class ThreadPoolExecutorTest {


    // 自定义线程池
    @Test
    public void test01(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1000, 1000, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
        threadPoolExecutor.prestartAllCoreThreads();
        //SleepUtils.second(20);
        while (true){
            threadPoolExecutor.execute(()->{
                System.out.println("hello threadpool");
            });
        }
    }


    @Test
    public void test05(){
        byte [] _1M=new byte[1024*1024];
        ArrayList<byte[]> bytes = new ArrayList<>();
        while (true){
            bytes.add(_1M);
            SleepUtils.second(1);
        }

    }

    @Test
    public void test02(){
        //固定核心线程数和最大线程数的线程池，使用的是有界队列，不建议使用
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(()->{
            System.out.println("hello FixedThreadPool");
        });

    }

    @Test
    public void test03(){
        // 只有一个线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            System.out.println("hello SingleThreadExecutor");
        });
    }

    @Test
    public void test04() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(() -> {
            return "hello future task";
        });
        String s = submit.get();
        System.out.println(s);
    }

}
