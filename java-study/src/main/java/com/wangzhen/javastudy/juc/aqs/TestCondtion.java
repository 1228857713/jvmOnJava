package com.wangzhen.javastudy.juc.aqs;

import com.wangzhen.javastudy.util.SleepUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * Datetime:    2020/9/9   8:19 下午
 * Author:   王震
 */
public class TestCondtion {
    static Logger logger = LoggerFactory.getLogger(TestCondtion.class);

    @Test
    public void testCondtion(){
        ReentrantLock lock = new ReentrantLock();
        Condition gameCondition = lock.newCondition();
        Condition moneyConditon = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                logger.debug("工作真无聊我想玩游戏");
                gameCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            logger.debug("开始玩游戏了");


        }).start();
        new Thread(()->{
            logger.debug("世界这么大，我想出去玩玩");
            lock.lock();
            try {
                moneyConditon.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug("你有钱了 去吧");
            lock.unlock();

        }).start();
        lock.lock();
        gameCondition.signal();
        moneyConditon.signal();
        lock.unlock();

        SleepUtils.second(1);

    }
}
