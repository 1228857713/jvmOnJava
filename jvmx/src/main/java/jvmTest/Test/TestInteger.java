package jvmTest.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
}