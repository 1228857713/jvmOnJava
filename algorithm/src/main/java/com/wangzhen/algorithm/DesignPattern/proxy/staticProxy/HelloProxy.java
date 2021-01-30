package com.wangzhen.algorithm.DesignPattern.proxy.staticProxy;

import com.wangzhen.algorithm.DesignPattern.proxy.dynamicProxy.Hello;
import com.wangzhen.algorithm.DesignPattern.proxy.dynamicProxy.HelloImpl;

/**
 * Description:
 * Datetime:    2020/10/26   8:26 下午
 * Author:   王震
 */
public class HelloProxy implements Hello {
    Hello hello;

    public HelloProxy() {
        this.hello = new HelloImpl();
    }

    @Override
    public void say() {
        System.out.println("before");
        hello.say();
        System.out.println("after");
    }

    public static void main(String[] args) {
        Hello hello = new HelloProxy();
        hello.say();
    }


}
