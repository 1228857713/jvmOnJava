package com.wangzhen.javastudy.juc.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 测试hashmap 相关的方法
 * Datetime:    2021/2/26   下午7:09
 * Author:   王震
 */
@Slf4j
public class TestHashMap {

    /**
     * @desc 测试HashMpa的扩容操作
     * HashMap 的默认容量是16 其 loadFactory 是0.75 所以单存放到第13个数据的时候应该会出发扩容操作
     */
    @Test
    public void test01(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,1);
        map.put(3,1);
        map.put(4,1);
        map.put(5,1);
        map.put(6,1);
        map.put(7,1);
        map.put(8,1);
        map.put(9,1);
        map.put(10,1);
        map.put(11,1);
        map.put(12,1);
        map.put(13,1);

    }
}
