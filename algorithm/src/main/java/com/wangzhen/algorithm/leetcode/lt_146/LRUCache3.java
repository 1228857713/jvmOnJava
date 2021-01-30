package com.wangzhen.algorithm.leetcode.lt_146;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: 通过linkedHashMap 实现Lru 缓存
 * Datetime:    2020/9/28   8:47 上午
 * Author:   王震
 */
public class LRUCache3 extends LinkedHashMap<Integer,Integer>{

    int capacity ;
    public LRUCache3(int capacity){
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }


    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    public Integer get(Object key) {
        return super.getOrDefault(key,-1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size()>capacity;
    }

    public static void main(String[] args) {
        LRUCache3 cache = new LRUCache3( 3 /* 缓存容量 */ );
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));//4
        System.out.println(cache.get(3));//3
        System.out.println(cache.get(2));//2
        System.out.println(cache.get(1));//-1
        cache.put(5, 5);
        System.out.println(cache.get(1));//-1
        System.out.println(cache.get(2));//2
        System.out.println(cache.get(3));//3
        System.out.println(cache.get(4));//-1
        System.out.println(cache.get(5));//5
    }
}
