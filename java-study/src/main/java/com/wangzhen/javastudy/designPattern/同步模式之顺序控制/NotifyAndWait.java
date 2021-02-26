package com.wangzhen.javastudy.designPattern.同步模式之顺序控制;

/**
 * 顺序打印 t1 ,t2, t3
 */
public class NotifyAndWait {
    static int flag=0;
    public static  void main(String[] args) {
        Object obj = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (obj){
                System.out.println("这是t1");
                obj.notifyAll();
                flag =1;
            }

        });
        Thread t2 = new Thread(() -> {
            synchronized (obj){
                while (!(flag == 1)) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("这是t2");
                flag =2;
            }

        });
        Thread t3 = new Thread(() -> {
            synchronized (obj){
                while (!(flag==2)) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("这是t3");
            }

        });


        t3.start();
        t2.start();
        t1.start();

    }
}
