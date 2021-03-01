package com.wangzhen.javastudy.juc.Lock;


import com.wangzhen.javastudy.util.SleepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lock {
    static  volatile  boolean  flag = true;
    private static final Logger logger = LoggerFactory.getLogger(Lock.class);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    System.out.println("");
                }
            }
        }).start();
        SleepUtils.second(1);
        flag = false;
        logger.info("停止打印");

    }


}
