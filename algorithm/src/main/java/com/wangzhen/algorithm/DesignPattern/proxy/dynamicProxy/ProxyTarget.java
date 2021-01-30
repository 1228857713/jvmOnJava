package com.wangzhen.algorithm.DesignPattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 * Datetime:    2020/10/31   4:48 下午
 * Author:   王震
 */
public class ProxyTarget implements InvocationHandler {

    // 持有目标接口类的引用
    private Object target;

    public ProxyTarget(Object target) {
        this.target = target;
    }

    public <T> T getProxy(Class interfaceClz){
        return (T) Proxy.newProxyInstance(interfaceClz.getClassLoader(),new Class[]{interfaceClz},this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("服务限流");
        Object invoke = method.invoke(target, args);

        return invoke;
    }
}
