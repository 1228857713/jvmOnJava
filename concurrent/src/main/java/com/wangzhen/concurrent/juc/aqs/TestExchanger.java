package com.wangzhen.concurrent.juc.aqs;

import com.wangzhen.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:  Exchanger 用于两个线程之间的数据交换,一个线程执行
 * Datetime:    2020/12/15   10:58
 * Author:   王震
 */
@Slf4j
public class TestExchanger {

    Exchanger<String> exchanger = new Exchanger<String>();
    ExecutorService executorService = Executors.newFixedThreadPool(2);


    @Test
    public void test1(){
        executorService.execute(()->{
            try {
                String a= "银行流水a";
                String b = exchanger.exchange(a);
                log.info(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            try {
                String b= "银行流水B";
                String a = exchanger.exchange(b);
                log.info("a的流水是{},b的流水是{}",a,b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        SleepUtils.second(1);
        executorService.shutdown();
    }
}