package com.wangzhen.concurrent.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {
    public static void main(String[] args) {
        Thread thread=new Thread();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo:threadInfos){
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo. getThreadName());
        }

    }
}
