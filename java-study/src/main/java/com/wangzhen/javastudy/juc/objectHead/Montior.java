package com.wangzhen.javastudy.juc.objectHead;


import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class Montior {
    public Lock lock=new Lock();
    int count =0;

    @Test
    public void testLock(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                add();

            }
        },"t2").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                add();
            }
        },"t1").start();

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }

    public  void add(){
        synchronized (lock){
           // SleepUtils.second(10);
            count++;
        }
    }
}
