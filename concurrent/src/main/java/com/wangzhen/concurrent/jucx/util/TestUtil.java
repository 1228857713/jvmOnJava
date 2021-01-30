package com.wangzhen.concurrent.jucx.util;

import com.wangzhen.concurrent.jucx.locks.ReentrantLock;
import org.junit.Test;
import sun.misc.Unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

public class TestUtil {
    int staus;

//    public static void main(String[] args) throws NoSuchFieldException {
//        TestUtil testUtil = new TestUtil();
//        int begin = testUtil.staus;
//        testUtil.staus = 1;
//        Unsafe unsafe = UnsafeUtil.getUnsfe();
//        long statusOffset = unsafe.objectFieldOffset(TestUtil.class.getDeclaredField("staus"));
//        unsafe.compareAndSwapObject(testUtil,statusOffset,null,2);
//        System.out.println(testUtil.staus );
//        System.out.println(begin);
//    }

    @Test
    public void testCAS() throws Exception {
        Node node = new Node();
        Node  n = node.prev;
        Node m = n;
        Unsafe unsafe = UnsafeUtil.getUnsfe();
        long prevNodeOffset = unsafe.objectFieldOffset(Node.class.getDeclaredField("prev"));
        unsafe.compareAndSwapObject(node,prevNodeOffset,null,new Node());
        System.out.println(node.prev);
        System.out.println(n);
        System.out.println(m);
    }

    @Test
    public void testPark(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    LockSupport.park();
                    System.out.println(i);
                }
            }
        });
        thread.start();
        for (int i = 0; i < 3; i++) {
            LockSupport.unpark(thread);
            SleepUtils.second(1);
        }





    }

    @Test
    public void testMyLock(){
        final ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("线程1 开始执行");
                    SleepUtils.second(3);
                }finally {

                    lock.unlock();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("线程2 开始执行");
                   // SleepUtils.second(1);
                }finally {

                    lock.unlock();
                }
            }
        });
        t1.start();
        t2.start();
        SleepUtils.second(5);

    }

    @Test
    public void testReentrantLock(){

    }

    public static void main(String[] args) {
        Map map = new HashMap();
    }
}
