package com.wangzhen.algorithm.DesignPattern.proxy.jdk;

/**
 * Description:
 * Datetime:    2020/12/2   下午7:04
 * Author:   王震
 */
public class HelloImpl implements Hello{
    @Override
    public void say(String msg) {
        System.out.println(msg);
    }
}
