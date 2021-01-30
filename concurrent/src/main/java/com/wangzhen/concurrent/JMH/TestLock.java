package com.wangzhen.concurrent.JMH;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: JMH 一款多线程测试工具,专门用于进行代码的微基准测试的一套工具API
 * Datetime:    2020/12/22   下午9:40
 * Author:   王震
 */
@Slf4j
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1) // 预热次数为一次
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS) // 迭代10次，每次持续1秒
@Threads(8) // 8个线程
@Fork(2)  // 2个进程
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class TestLock {

    public void fn(){
        Map map = new HashMap();
        for (int i = 0; i < 1000; i++) {
            map.put(i,i);
        }
    }

    @Benchmark
    public void TestSync(){
        synchronized (TestLock.class){
            fn();
        }
    }

    @Benchmark
    public void TestLock(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            fn();
        }finally {
            lock.unlock();
        }
    }




    @Test
    public void test1() throws RunnerException {
        Options opt = new OptionsBuilder().
                include(TestLock.class.getSimpleName()).
                build();
        new Runner(opt).run();
    }



}
