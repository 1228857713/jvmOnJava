package com.wangzhen.concurrent.ThreadPool.threadPool.myself;

import java.util.HashSet;

/**
 * Description:
 * Datetime:    2020/12/2   下午8:58
 * Author:   王震
 */
public class ThreadPool {
    // 存放待办任务。
    public BlockQueue<Thread> blockQueue ;
    public int maxTask;
    // 最大的线程数量
    public int maxThreads;
    // 存放工作的线程。
    public HashSet<work> works;

    public ThreadPool(int maxTask, int maxThread) {
        this.blockQueue = new BlockQueue();
        this.maxTask = maxTask;
        this.maxThreads = maxThreads;
        this.works = new HashSet<>();
    }

    public void execute(Thread thread){
        if(works.size()>=maxThreads){
            blockQueue.put(thread);
        }else{
            work work = new work(thread);
            work.start();
        }



    }

    class work{
        public  Runnable task;

        public work(Runnable runnable) {
            this.task = runnable;
        }
        public void start(){
            while(task!=null||(task=blockQueue.take())!=null){
                task.run();
                task=null;
            }
            works.remove(this);
        }
    }
}
