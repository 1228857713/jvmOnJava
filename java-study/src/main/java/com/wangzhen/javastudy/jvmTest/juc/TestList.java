package com.wangzhen.javastudy.jvmTest.juc;

import com.wangzhen.javastudy.jvmTest.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 测试juc中的list类
 * Datetime:    2021/2/1   11:06
 * Author:   王震
 */

@Slf4j
public class TestList {


    /**
     *  1.ArrayList 容器如果在读取数据的时候有其他线程在修改数据那么就会抛出 ConcurrentModificationException 异常
     *  2.可以使用CopyOnWriteArrayList 类来解决该问题
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            integers.add(i);
        }
        Thread thread = new Thread(() -> {
            int i =0;
            while (true){
                integers.add(i++);
            }
        });
        thread.start();
//        Iterator<Integer> iterator = integers.iterator();
//        while (iterator.hasNext()){
//            SleepUtils.sleep(1);
//            System.out.println(iterator.next());
//        }
        for (Integer integer : integers) {
            SleepUtils.sleep(1);
            System.out.println(integer);
        }
        System.in.read();
    }
}