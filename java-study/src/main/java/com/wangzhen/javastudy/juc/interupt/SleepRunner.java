package com.wangzhen.javastudy.juc.interupt;

import com.wangzhen.javastudy.util.SleepUtils;

public class SleepRunner implements Runnable{
    @Override
    public void run() {
        while (true){
            SleepUtils.second(10);

        }
    }
}
