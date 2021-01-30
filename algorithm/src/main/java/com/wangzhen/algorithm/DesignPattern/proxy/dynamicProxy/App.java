package com.wangzhen.algorithm.DesignPattern.proxy.dynamicProxy;

/**
 * Description:
 * Datetime:    2020/10/28   5:24 下午
 * Author:   王震
 */
public class App {
    public static void main(String[] args) {
        // 会将jdk 动态代理生成的类 输出到磁盘上。
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        ProxyTarget proxyTarget = new ProxyTarget(new HelloImpl());
        Hello proxy = proxyTarget.getProxy(Hello.class);
        proxy.say();

    }
}
