package com.wangzhen.javastudy.jvm.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Description:
 * Datetime:    2021/1/26   10:15
 * Author:   王震
 */

@Slf4j
public class TestList {



    /*
      list 删除数据应该使用 iterator 而不是foreach
      这段测试代码会 报错java.util.ConcurrentModificationException
     */
    @Test
    public void test01(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        for (String string : strings) {
            if("2".equals(string)){
                 strings.remove(string);
            }
        }
        System.out.printf(strings.toString());
    }
}