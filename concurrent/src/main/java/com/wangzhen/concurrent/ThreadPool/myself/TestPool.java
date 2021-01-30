package com.wangzhen.concurrent.ThreadPool.myself;

import com.wangzhen.concurrent.util.SleepUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Datetime:    2020/10/21   9:12 下午
 * Author:   王震
 */


public class TestPool {

    @Test
    public void test1() {
        ThreadPool threadPool = new ThreadPool(2, 2000, TimeUnit.MILLISECONDS, 10);
        for (int i = 0; i < 15; i++) {
            int j = i;
            threadPool.execute(()->{
                SleepUtils.second(2);
                System.out.println(j);
            });
        }
    }

    public void test2(){

    }
}
