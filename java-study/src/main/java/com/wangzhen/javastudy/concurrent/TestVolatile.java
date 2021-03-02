package com.wangzhen.javastudy.concurrent;

import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description: 测试volatile相关的内容
 * Datetime:    2021/2/2   上午10:56
 * Author:   王震
 */

@Slf4j
public class TestVolatile {
    //volatile
    boolean flag = true;

    /**
     * @description
     * 1.如果不加 volatile修饰flag 变量,那么就不具备内存可见性一个线程修改的结果，另一个线程可能一直看不到
     * 2.如下例子 如果 flag 不加volatile关键字修饰那么就会一直不打印`好好学习天天向上`
     * 3.很多方法虽然表明和锁无关实际内部实现却加了锁，如 #test02()
     *
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

    /**
     *  以下很多方法的调用都有加锁机制导致访问主内存
     * @throws InterruptedException
     */
    @Test
    public void test02() throws InterruptedException {
        Thread studyThread = new Thread(() -> {
            while (true){
                // 这个也会影响线程的结果
                SleepUtils.second(1);
                if(flag){
                    log.info("好好学习天天向上");
               } else{
                    // 增加下面的 else 代码都会影响 test01的结构
                    // 因为这些代码里面有锁机制
                    log.info("我要打游戏");
                    System.out.println("我要打游戏");
                }
            }
        });

        Thread gameThread = new Thread(() -> {
            SleepUtils.second(1);
            flag = false;
        });
        studyThread.start();
        gameThread.start();
        studyThread.join();
        gameThread.join();

    }
}
