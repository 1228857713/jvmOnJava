package com.wangzhen.javastudy.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * Datetime:    2021/1/28   14:58
 * Author:   王震
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1",1);
        concurrentHashMap.put("2",2);
        concurrentHashMap.put("3",3);
        concurrentHashMap.put("4",4);
        concurrentHashMap.put("5",5);
        System.out.println(concurrentHashMap.get("1"));
    }
}