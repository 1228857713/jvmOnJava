package com.wangzhen.javastudy.jvmTest.jdk;

import com.wangzhen.javastudy.jvmTest.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Datetime:    2021/1/26   9:57
 * Author:   王震
 */
@Slf4j
public class TestHashMap {


    /**
     * 在对map 进行 entrySet 的时候，不能对其进行 put 操作
     * 这里没有测试出来错误
     */
    @Test
    public void test01(){
        HashMap map = new HashMap<String,String>();
        Thread keySetThread = new Thread(() -> {
            while (true){
                Set set = map.entrySet();
            }
        }, "keySetThread");

        Thread putThread = new Thread(() -> {
            while (true){
                map.put("hello","world");
            }
        }, "putThread");

        keySetThread.start();
        putThread.start();
        SleepUtils.sleep(10);
    }


    /**
     * Collections.emptyList(); 是返回的 静态的list 不能添加数据
     */
    @Test
    public void test02(){
        List<Object> objects = Collections.emptyList();
        objects.add("str");
    }

    /**
     * hashmap key 值可以为空，concurrentHashMap 的key 值为空嘛
     */
    @Test
    public void  test03(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put(null,10);
        System.out.println(map.get(null));
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(null,10);

    }
}