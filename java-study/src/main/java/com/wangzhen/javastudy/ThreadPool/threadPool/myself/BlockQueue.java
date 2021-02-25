package com.wangzhen.javastudy.ThreadPool.threadPool.myself;



import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 阻塞队列,用于存放待办的任务数
 * Datetime:    2020/12/2   下午8:39
 * Author:   王震
 */
@Slf4j
public class BlockQueue<T> {
    public ArrayDeque<T> queue ;
    public ReentrantLock lock;
    Condition emptyWaitSet;
    Condition fullWaitSet;
    // 最多存放的待办的任务数量
    public int maxSize;
    public BlockQueue() {
        queue = new ArrayDeque<>();
        // 当队列是空的时候的时候等待
        lock = new ReentrantLock();
        emptyWaitSet = lock.newCondition();
        // 满等待
        fullWaitSet = lock.newCondition();
    }

    //添加任务到队列
    public void put(T t){
        try {
            lock.lock();
            while (true){
                if(queue.size()>=maxSize){
                    try {
                        fullWaitSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    break;
                }
            }
            queue.addLast(t);
            emptyWaitSet.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take(){
        try {
            lock.lock();
            while (true){
                if(queue.size()==0){
                    emptyWaitSet.await();
                }else {
                    break;
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }catch (Exception e){
            log.info(e.toString());
        }finally {
            lock.unlock();
        }
        return null;
    }
}
