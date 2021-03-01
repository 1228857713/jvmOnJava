package com.wangzhen.javastudy.jmh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Description: 测试 HashMap SyncHashMap 和 ConcurrentHashMap 三个map的性能
 * Datetime:    2020/12/23   下午7:53
 * Author:   王震
 */

@Slf4j
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1) // 预热次数为一次
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS) // 迭代10次，每次持续1秒
@Threads(2) //
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class TestMap {
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static Map<Integer, Integer> syncHashMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    static ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();


    // 数据准备
    @Setup
    public void setup(){
        for (int i = 0; i < 1000; i++) {
            hashMap.put(i, i);
            syncHashMap.put(i, i);
            concurrentHashMap.put(i, i);
        }
    }



    @Benchmark
    public void hashMapGet(){
        hashMap.get(4);
    }
    @Benchmark
    public void syncHashMapGet(){
        syncHashMap.get(4);
    }
    @Benchmark
    public void concurrentHashMapGet(){
        concurrentHashMap.get(4);
    }
//    @Benchmark
//    public void hashMapSize(){
//        hashMap.size();
//    }
//    @Benchmark
//    public void syncHashMapSize(){
//        syncHashMap.size();
//    }
//    @Benchmark
//    public void concurrentHashMapSize(){
//        concurrentHashMap.size();
//    }


    @Test
    public void test1() throws RunnerException {
        Options opt = new OptionsBuilder().
                include(TestMap.class.getSimpleName()).
                build();
        new Runner(opt).run();
    }



}
