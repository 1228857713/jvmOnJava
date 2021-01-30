package com.wangzhen.concurrent.cacheLine;

public class test1 {
    public static long[] array= new long[2];
    public static volatile long length = 1000000000l;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=0;i<length;i++){
                    array[0]=1;

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=0;i<length;i++){
                    array[0]=2;

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
