package com.wangzhen.jvmTest.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * Description: Integer一些相关的测试类
 * Datetime:    2021/1/29   9:49
 * Author:   王震
 */
@Slf4j
public class TestInteger {


    // 新建的 Integer 对象 肯定是两个不同的对象
    @Test
    public void test01(){
        Integer _127 = new Integer(126);
        Integer _127_2 = new Integer(126);
        //false
        System.out.println(String.valueOf(_127==_127_2));

        /**
         * Integer.valueOf() 方法源码中会从缓存IntegerCache查找数据 缓存范围默认是 -128->127
         * 这也是享元模式的一种体现。
         */
        Integer integer = 127;// 自动装箱 等价于 Integer.valueOf(127)
        Integer integer2 = 127;
        // true
        System.out.println(integer==integer2);

        Integer integer3 = 128;//自动装箱 等价于 Integer.valueOf(127)
        Integer integer4 = 128;

        //false
        System.out.println(integer3==integer4);
    }


    /**
     * 测试 Integer 对象占用的内存的大小：
     *      1.在开启指针压缩的情况下(默认开启) 为 16个字节，MarkWorlds8+classpoint4+int4
     *      2.在未开启指针压缩的情况下（-XX:-UseCompressedOops） 为24字节 MarkWorlds8+classpoint8+int4+4（对象填充）
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        Integer _1000 = 1000;
        int i = 10;
        Long l = 10000000l;
        System.in.read();
    }

    // 浮点类型计算 并不准确，需要使用 java中math函数，BigDecimal 来计算。
    @Test
    public void test03(){
        float f2 = 0.45f/3;
        System.out.println(f2);
    }

}