package com.wangzhen.algorithm.collection.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;

/**
 * Description:
 * Datetime:    2021/1/22   10:38
 * Author:   王震
 */
public class TestLinkedHashMap {



    // 通过测试类可以看出,linkedHashMap 内部通过链表维护了一个有序队列，保证了有序性
    // 而hash map 是无须的
    @Test
    public void test01(){
        LinkedHashMap<Integer,String> map = new LinkedHashMap();
        for (int i = 100; i >0; i--) {
            map.put(i,i+"");
        }
        for (Integer integer : map.keySet()) {
            System.out.println(map.get(integer));
        }
    }

    @Test
    public void test02(){

        HashMap<Integer,String> map = new HashMap<>();
        for (int i = 100; i >0; i--) {
            map.put(i,i+"");
        }
        for (Integer integer : map.keySet()) {
            System.out.println(map.get(integer));
        }
    }
}