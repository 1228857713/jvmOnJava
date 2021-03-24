package util.concurrent.atomic;


import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wangzhen
 * @date: 2021/3/17 13:16
 * @desc:  测试原子类相关的操作
 */
@Slf4j
public class AtomicIntegerTest {

    @Test
    public void testABA() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        Thread thread = new Thread(() -> {
            atomicInteger.compareAndSet(10,11);
            atomicInteger.compareAndSet(11,10);
        });
        thread.start();
        Thread thread1 = new Thread(() -> {
            SleepUtils.second(1);
            atomicInteger.compareAndSet(10,11);
        });
        thread1.start();
        thread.join();
        thread1.join();
        // 去除11 说明有ABA 问题
        log.info(atomicInteger.get()+"");
    }
}