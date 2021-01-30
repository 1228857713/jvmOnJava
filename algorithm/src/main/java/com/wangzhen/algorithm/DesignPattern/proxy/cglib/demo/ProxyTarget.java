package com.wangzhen.algorithm.DesignPattern.proxy.cglib.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description:
 * Datetime:    2020/10/29   8:38 下午
 * Author:   王震
 */
public class ProxyTarget implements MethodInterceptor {

    public <T> T getProxy(Class clz){
        // 字节码增强的一个类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(clz);
        //设置回调类
        enhancer.setCallback(this);
        return (T) enhancer.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("服务限流.....");
        methodProxy.invokeSuper(o,objects);
        return methodProxy;
    }
}
