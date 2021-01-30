package com.wangzhen.concurrent.jucx.test;

import com.wangzhen.concurrent.jucx.locks.ReentrantLock;
import org.junit.Test;

import java.util.ArrayList;

public class testMyLock {
    public static int sum  = 0;

    public void add(){
        for (int i = 0; i <1000000 ; i++) {
            sum++;
        }
    }

    @Test
    public void  testLock() throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    add();
                   // lock.unlock();
                }
            });
            threads.add(thread);
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
        }
     //   SleepUtils.second(3);
        System.out.println(sum);

    }
}
