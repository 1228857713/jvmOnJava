package com.wangzhen.javastudy.jvm.Socket;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: wangzhen
 * @date: 2021/3/10 9:25
 * @desc: 测试类的初始化流程
 */
@Slf4j
public class ClinitTest {
    static {
        log.info("before init");
        int b = 3 /0;
        log.info("after init");
    }
    public void sayName(){
        log.info("hello world!");
    }

    @Test
    public void test01(){
        ClinitTest clinitTest = new ClinitTest();
        clinitTest.sayName();
    }
}