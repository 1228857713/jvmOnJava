package com.wangzhen.javastudy.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Description:
 * Datetime:    2021/2/28   下午3:59
 * Author:   王震
 */
@Slf4j
public class TestStream {


    /**
     * @description stream 的基本操作
     *  Stream::filter  过滤
     *  Stream::foreach  迭代
     *  Stream::distinct 去重
     *  Stream::limit 限制前几个
     *  Stream::skip  跳过前几个
     */
    @Test
    public void test01(){
        List<Integer> list = Arrays.asList(4,5,231,5,43,65,43,2,5,4);
        list.stream().filter((x)->{
            return x>10;
        }).forEach(System.out::println);
        System.out.println("-------------");
        list.stream().distinct().forEach(System.out::println);
        list.stream().limit(2).forEach(System.out::println);
        list.stream().skip(2).forEach(System.out::println);
    }


    /**
     * @description map 映射
     */
    @Test
    public void test02(){
        TestStream testStream = new TestStream();
        List<String> strings = Arrays.asList("a", "bb", "cc", "dd", "dfd");
        strings.stream().map((str)->{
            return str.toUpperCase();
        }).forEach(log::info);
    }

    /**
     * @description map 映射
     */
    @Test
    public void test03(){
        TestStream testStream = new TestStream();
        List<String> strings = Arrays.asList("a", "bb", "cc", "dd", "dfd");
        strings.stream().map((str)->{
            return str.toUpperCase();
        }).forEach(TestStream::sayName);

        strings.stream().map((str)->{
            return str.toUpperCase();
        }).forEach(testStream::sayName2);

    }

    public  void sayName2(String s){
        log.info("wangzhen");
    }

    public static void sayName(String s){
        log.info("wangzhen");
    }
}
