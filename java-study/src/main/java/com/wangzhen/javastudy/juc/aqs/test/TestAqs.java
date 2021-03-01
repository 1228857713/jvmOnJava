package com.wangzhen.javastudy.juc.aqs.test;

import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Description:
 * Datetime:    2020/12/14   15:28
 * Author:   王震
 */

@Slf4j
public class TestAqs {
    MyLock lock = new MyLock();

    public void fn(){
        lock.lock();
        try {
            log.info("{}线程获取锁,线程状态为{}",Thread.currentThread().getName(),Thread.currentThread().getState());
            SleepUtils.second(3);

        }finally {
            log.info("{}线程释放锁,线程状态为{}",Thread.currentThread().getName(),Thread.currentThread().getState());
            lock.unlock();

        }
    }

    @Test
    public void test1() throws IOException {
        new Thread(()->{
            fn();
        },"thread1").start();

        new Thread(()->{
            fn();
        },"thread2").start();

        System.in.read();
    }

}

class MyLock implements Lock{
    MySync mySync = new MySync();

    class MySync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,arg)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }else {
                return false;
            }
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState()==0;
        }
    }


    @Override
    public void lock() {
        // 获取锁
        mySync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1,1000*1000);
    }

    @Override
    public void unlock() {
        mySync.release(1);
    }

    @NotNull
    @Override
    public Condition newCondition() {
        return mySync.new ConditionObject();
    }
}