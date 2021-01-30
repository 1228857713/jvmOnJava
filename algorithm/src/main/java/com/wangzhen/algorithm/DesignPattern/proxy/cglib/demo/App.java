package com.wangzhen.algorithm.DesignPattern.proxy.cglib.demo;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * Description:
 * Datetime:    2020/10/29   9:01 下午
 * Author:   王震
 */
public class App {
    public static void main(String[] args) {

        // 将动态生成的类 存储到磁盘上。
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"/Users/wangzhen/Desktop/workspace/java/jvm/jvmOnJava/algorithm/src/main/java/com/wangzhen/algorithm/设计模式/代理模式/temp");
        ProxyTarget proxyTarget = new ProxyTarget();
        Hello proxy = proxyTarget.getProxy(HelloImpl.class);
        proxy.say();

    }
}
