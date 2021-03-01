package com.wangzhen.javastudy.jvm.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * Description: 测试内存泄露，需要增加 -Xms1m -Xmx1m 不然很难达到内存溢出
 * Datetime:    2020/12/21   11:32
 * Author:   王震
 * result：java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
@Slf4j
public class TestMemoryLeak {
    static class Key{
        Integer id;

        public Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    @Test
    public void test1(){
        HashMap<Key, String> map = new HashMap<>();
        while (true){
            for (int i = 0; i < 10000; i++) {
                if(!map.containsKey(new Key(i))){
                    map.put(new Key(i),"number:"+i);
                }
            }
        }
    }

}