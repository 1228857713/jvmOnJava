package com.wangzhen.javastudy.ThreadPool.myself;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Datetime:    2020/10/21   9:56 下午
 * Author:   王震
 */
public class ThreadPool {
    BlockQueue<Runnable> taskQueue;
    HashSet<Work> works ;

    // 线程的数量
    private int coreSize;

    // 超时时间
    long timeout;
    // 时间单位
    TimeUnit timeUnit;

    public ThreadPool( int coreSize, long timeout, TimeUnit timeUnit,int queueCapacity) {
        this.taskQueue = new BlockQueue<>(queueCapacity);
        this.works = new HashSet<>();
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public void execute(Runnable task){
        synchronized (works){
            if(works.size()<coreSize){
                Work work = new Work(task);
                System.out.println("新增work线程:"+work.toString());
                works.add(work);
                work.start();
            }else {
                taskQueue.put(task);
                System.out.println("任务队列添加线程");
            }
        }

    }

    class Work extends Thread{
        private Runnable task;

        public Work( Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 执行任务
            // 如果task 不为空，直接执行task
            // 如果task 为空，那么就从 blockqueue 队列中拿出任务执行
            while (task!=null || (task = taskQueue.take(timeout,timeUnit))!=null){
                try{
                    System.out.println("正在执行任务"+task.toString());
                    task.run();
                }catch (Exception e){

                }finally {
                    task = null;
                }
            }
            synchronized (works){
                System.out.println(this.toString()+"线程被移除了");
                works.remove(this);
            }

        }
    }
}
