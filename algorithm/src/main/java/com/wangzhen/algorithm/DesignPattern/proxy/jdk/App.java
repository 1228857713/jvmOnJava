package com.wangzhen.algorithm.DesignPattern.proxy.jdk;

import org.junit.Test;

/**
 * Description:
 * Datetime:    2020/12/2   下午6:53
 * Author:   王震
 */

public class App {
    @Test
    public void test1() throws InterruptedException {
        Hello object = (Hello) new ProxyTarget(null).getObject(Hello.class);
        object.say("hello world");
        Thread.sleep(1000000);
    }

    @Test
    public void test2() throws InterruptedException {
        HelloImpl helloImpl = new HelloImpl();
        Hello object = (Hello) new ProxyTarget(helloImpl).getObject(Hello.class);
        object.say("hello world");
        Thread.sleep(1000000);
    }
}
