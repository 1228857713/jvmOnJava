package com.wangzhen.concurrent.cacheLine;

public class test2 {
    public static long[] array= new long[16];

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=0;i<test1.length;i++){
                    array[0]=1;

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=0;i<test1.length;i++){
                    array[7]=2;

                }

            }
        });
        final long start_time = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        final long end_time = System.nanoTime();
        System.out.println((end_time-start_time)/1000000);

    }
}
