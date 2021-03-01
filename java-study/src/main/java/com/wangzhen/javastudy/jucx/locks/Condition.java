package com.wangzhen.javastudy.jucx.locks;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public interface Condition {
    //当前线程进入等待状态
    void await() throws InterruptedException;
    void awaitUninterruptibly();
    long awaitNanos(long nanosTimeout) throws InterruptedException;
    boolean await(long time, TimeUnit unit)throws InterruptedException;
    boolean awaitUntil(Date deadline)throws  InterruptedException;

    //唤醒 等待在这个 Conditon 上的 线程
    void signal();

    void signalAll();
}
