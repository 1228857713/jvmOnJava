package com.wangzhen.concurrent.juc.notifyAndWait;

import com.wangzhen.concurrent.util.SleepUtils;
import org.junit.Test;

public class NnotifyAndWait {
    public Object lock = new Object();

    @Test
    public void fn() throws InterruptedException {
        // 如果 对象没有被枷锁状态，就没有和moitor 对象相关联
        // 直接调用会报错
        lock.wait();
    }

    @Test
    public void fn2() throws InterruptedException {
        // 如果 对象没有被枷锁状态，就没有和moitor 对象相关联
        // 直接调用会报错
        synchronized (lock){
            lock.wait();
        }
    }

    @Test
    public void testWait() throws InterruptedException {
        synchronized (lock){
            //等待1秒钟，如果没有其他线程唤醒那么就会 继续执行下去
            lock.wait(1000);
        }
        System.out.println("继续执行代码");
    }

    @Test
    public void testNotify(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("执行t1代码");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("继续执行t1代码");
                }
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println("执行t2代码");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("继续执行t2代码");
                }
            }
        },"t2").start();

        SleepUtils.second(2);
        synchronized (lock){
            System.out.println("唤醒其他线程");
            lock.notify();
           // lock.notifyAll();
        }
    }
}
