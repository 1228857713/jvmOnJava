package com.wangzhen.concurrent.juc.Lock.DeadLock;

import com.wangzhen.concurrent.util.SleepUtils;
import org.junit.Test;

public class TestDeadLock {

    public static  Object lockA= new Object();
    public static Object lockB= new Object();

    @Test
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockA){
                    System.out.println("线程1 开始操作");
                    SleepUtils.second(2);
                    synchronized (lockB){
                        System.out.println("线程1 结束操作");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB){
                    System.out.println("线程2 开始操作");
                    SleepUtils.second(1);
                    synchronized (lockA){
                        System.out.println("线程2 结束操作");
                    }
                }
            }
        }).start();


    }
}
