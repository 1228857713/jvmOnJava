package com.wangzhen.javastudy.juc.Lock;

import com.wangzhen.javastudy.util.SleepUtils;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁
 *  偏向锁 默认在程序启动后才会开启，所以程序正常启动后的对象头 都是偏向锁的
 *  -XX:BiasedLockingStartupDelay=0 来禁用延迟
 */
public class Biased {
    @Test
    public void  testBiased(){

        System.out.println(ClassLayout.parseInstance(new Lock()).toPrintable());
        SleepUtils.second(5);
        System.out.println(ClassLayout.parseInstance(new Lock()).toPrintable());
    }

    @Test
    public void  testBiased2(){
        Lock lock = new Lock();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock){
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }


}


