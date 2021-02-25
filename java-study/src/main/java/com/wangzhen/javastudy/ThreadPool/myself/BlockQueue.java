package com.wangzhen.javastudy.ThreadPool.myself;



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * Datetime:    2020/10/21   9:11 下午
 * Author:   王震
 */
public class BlockQueue<T> {
    public BlockQueue(int capacity) {
        this.capacity = capacity;
    }

    // 存放任务的 双向队列
    Deque<T> queue = new ArrayDeque<>();

    // 锁对象
    ReentrantLock lock = new ReentrantLock();

    // 队列为空 等待
    Condition emptyWaitSet = lock.newCondition();
    // 队列满了 等待
    Condition fullWaitSet = lock.newCondition();
    // 线程队列的容量
    public int capacity;

    public T take(long timeout, TimeUnit timeUnit){
        long l = timeUnit.toNanos(timeout);
        lock.lock();
        try {
            while (queue.isEmpty()){
                try {
                    if(timeout<=0)
                        return null;
                    timeout = emptyWaitSet.awaitNanos(l);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;


        }finally {
            lock.unlock();
        }
    }

    public T take (){
        lock.lock();
        try {
                while (queue.isEmpty()){
                    try {
                        emptyWaitSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                T t = queue.removeFirst();
                fullWaitSet.signal();
                return t;


        }finally {
            lock.unlock();
        }
    }

    public void put (T element){
        lock.lock();
        try {
            while (queue.size()==capacity){
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }

    }



}
