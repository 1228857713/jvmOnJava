package com.wangzhen.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AtomicInteger {
    public static void main(String[] args) throws InterruptedException {
        int length = 100000;
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.setValue(100000000);
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Thread t =new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    // 调用原子方法
                    //atomicInteger.decrementCAS(1);
                    atomicInteger.decrement(1);
                }
            });
            threads.add(t);
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
        }
        System.out.println(atomicInteger.getValue());
    }

    private   int value;
    private static Unsafe unsafe;
    private static long valueOffset;
    static {
        unsafe = UnsafeUtils.getUnsafe();
        try {
            Field valueField =AtomicInteger.class.getDeclaredField("value");
            valueOffset  = unsafe.objectFieldOffset(valueField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // 减去amount
    public void decrementCAS(int amount){
        while (true){
            int prev = this.value;
            int next = prev - amount;
            if(unsafe.compareAndSwapInt(this,valueOffset,prev,next)){
                break;
            }

        }
    }
    // 减去amount
    public void decrement(int amount){
        this.value -=amount;
    }



}
