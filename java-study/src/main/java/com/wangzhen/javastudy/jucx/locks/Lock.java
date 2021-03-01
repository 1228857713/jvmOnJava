package com.wangzhen.javastudy.jucx.locks;



import java.util.concurrent.TimeUnit;

public interface Lock {
    void lock();


    // 可打断的加锁
    void lockInterruptibly() throws InterruptedException;

    boolean tyrLock();

    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    void unlock();


     Condition newConDition();

}
