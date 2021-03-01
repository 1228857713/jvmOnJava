package com.wangzhen.javastudy.juc.interupt;

import com.wangzhen.javastudy.util.SleepUtils;

public class Interupted {
    public static void main(String[] args) {
        Thread busyRunner = new Thread(new BusyRunner(), "BusyRunner");
        busyRunner.setDaemon(true);
        Thread sleepRunner = new Thread(new SleepRunner(), "SleepRunner");
        sleepRunner.setDaemon(true);
        busyRunner.start();
        sleepRunner.start();
        // 休眠5s 让 两个线程快速运行
        SleepUtils.second(5);
        busyRunner.interrupt();
        sleepRunner.interrupt();
        System.out.println("busyRunner 是否打断"+busyRunner.isInterrupted());
        System.out.println("sleepRunner 是否打断"+sleepRunner.isInterrupted());
        // 防止两个线程立刻退出
        SleepUtils.second(2);

    }
}
