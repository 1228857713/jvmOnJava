package com.wangzhen.concurrent.jmm;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources;
import com.wangzhen.concurrent.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description: 测试volatie相关的内容
 * Datetime:    2021/2/2   上午10:56
 * Author:   王震
 */

@Slf4j
public class TestVolatile {
    //volatile
     static boolean  flag = false;

    /**
     * 如果不加 volatile 修饰flag 变量,那么就不具备内存可见性
     * 一个线程修改的结果，另一个线程可能一直看不到
     * @throws InterruptedException
     */
    @Test
    public void test01() throws InterruptedException {
        Thread studyThread = new Thread(() -> {
            while (true){
                if(flag){
                    log.info("好好学习天天向上");
                }
            }
        });

        Thread gameThread = new Thread(() -> {
            SleepUtils.second(1);
            flag = true;
        });
        studyThread.start();
        gameThread.start();
        studyThread.join();
        gameThread.join();

    }

    @Test
    public void test02() throws InterruptedException {
        Thread studyThread = new Thread(() -> {
            while (true){
                // 这个也会影响线程的结果
                SleepUtils.second(1);
                if(flag){
                    log.info("好好学习天天向上");
                }else{
                    // 增加下面的 else 代码都会影响 test01的结构
                    // 因为这些代码里面有锁机制
                    log.info("我要打游戏");
                    System.out.println("我要打游戏");
                }
            }
        });

        Thread gameThread = new Thread(() -> {
            SleepUtils.second(1);
            flag = true;
        });
        studyThread.start();
        gameThread.start();
        studyThread.join();
        gameThread.join();

    }
}
