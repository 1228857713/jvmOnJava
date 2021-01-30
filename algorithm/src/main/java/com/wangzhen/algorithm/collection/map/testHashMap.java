package com.wangzhen.algorithm.collection.map;


import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Description:
 * Datetime:    2020/9/27   3:16 下午
 * Author:   王震
 */
public class testHashMap {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(3,3);
        map.put(2,2);

        map.put(4,4);
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println(map.get(iterator.next()));
        }
    }

    @Test
    public  void test2(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2,2);
        map.put(2,3);
        Iterator iterator = map.keySet().iterator();
        System.out.println(map.size());
        while (iterator.hasNext()){
            System.out.println(map.get(iterator.next()));
        }
    }
}
