package com.wangzhen.concurrent.JMH;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Datetime:    2020/12/23   上午11:28
 * Author:   王震
 */
@Slf4j
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1) // 预热次数为一次
@Measurement(iterations = 10000, time = 1, timeUnit = TimeUnit.MILLISECONDS) // 迭代10次，每次持续1秒
@Threads(8) // 8个线程
@Fork(2)  // 2个进程
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class TestDcl {

    @Benchmark
    public void TestDCL(){
        DCL instance = DCL.getInstance();
        instance.sayHello();
    }

    @Test
    public void test1() throws RunnerException {
        Options opt = new OptionsBuilder().
                include(TestDcl.class.getSimpleName()).
                build();
        new Runner(opt).run();
    }
}
