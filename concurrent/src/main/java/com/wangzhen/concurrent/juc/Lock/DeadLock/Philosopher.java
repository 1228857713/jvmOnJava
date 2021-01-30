package com.wangzhen.concurrent.juc.Lock.DeadLock;

import com.wangzhen.concurrent.util.SleepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Philosopher extends Thread{
    private static final Logger logger = LoggerFactory.getLogger(Philosopher.class);

    Chopsticks left;
    Chopsticks right;
    public String name;
    public Philosopher(String name,Chopsticks left,Chopsticks right){

        super(name);
        this.left=left;
        this.right=right;
    }
    public void eat(){
        logger.info("开始就餐");

    }

    public static void main(String[] args) {
        Chopsticks c1 = new Chopsticks("筷子1");
        Chopsticks c2 = new Chopsticks("筷子2");
        Chopsticks c3 = new Chopsticks("筷子3");
        Chopsticks c4 = new Chopsticks("筷子4");
        Chopsticks c5 = new Chopsticks("筷子5");
        Philosopher p1 =new Philosopher("亚里士多德",c1,c2);
        Philosopher p2 =new Philosopher("爱迪生",c2,c3);
        Philosopher p3 =new Philosopher("亚历山大",c3,c4);
        Philosopher p4 =new Philosopher("钱学森",c4,c5);
        Philosopher p5 =new Philosopher("袁隆平",c5,c1);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }


    @Override
    public void run() {
        while (true){
            synchronized (left){
                synchronized (right){
                    eat();
                }
            }
            SleepUtils.second(1);
        }
    }
}



class Chopsticks{
    String name;

    public Chopsticks(String name) {
        this.name = name;
    }
}