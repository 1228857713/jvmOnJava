package com.wangzhen.algorithm.DesignPattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 * Datetime:    2020/12/2   下午6:54
 * Author:   王震
 */
public class ProxyTarget<T> implements InvocationHandler {

   public Object obj;

    public ProxyTarget(Object obj) {
        this.obj = obj;
    }

    public   <T> T getObject(Class<T> clz){
        return (T) Proxy.newProxyInstance(clz.getClassLoader(),new Class[]{clz},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始打印");
        method.invoke(obj,args);
        System.out.println("结束打印");
        return null;
    }
}
