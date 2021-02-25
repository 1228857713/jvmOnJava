package com.wangzhen.javastudy.pattern.保护性暂停;

import java.util.concurrent.locks.LockSupport;


// 顺序执行保证 t2 线程在t1 线程之前执行
public class ParkAndUnPark {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("执行t1 线程");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("执行t2 线程");
            LockSupport.unpark(t1);
        });
        t2.start();
        t1.start();

    }
}
