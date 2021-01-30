package com.wangzhen.concurrent.juc.interupt.Sort;

public class Volatile {
    public static volatile int n =0;
    public static void increase(){
        n++;
    }

    public static void main(String[] args) throws InterruptedException {
        int nums =20;
        Thread[] threads = new Thread[nums];
        for(int i =0;i<nums;i++){
            threads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (this){
                        for (int i=0;i<1000;i++){
                            //increase();
                            n++;
                        }
                    }

                }
            });
            threads[i].start();
        }
        for(int i =0;i<nums;i++){
            threads[i].join();
        }
        System.out.println(n);

    }
}
