package com.wangzhen.dubbox.App.entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description:
 * Datetime:    2020/11/25   10:26
 * Author:   王震
 */
public class DynamicProxy implements InvocationHandler {


    public  <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equalsIgnoreCase("sayName")) {
            System.out.println("hello this is you name"+ args[0]);
        }
        if (method.getName().equalsIgnoreCase("sayAge")) {
            System.out.println("hello this is you age"+ args[0]);
        }
        if (method.getName().equalsIgnoreCase("getArrys")) {
            Integer [] array = (Integer[]) args;
            for (Integer integer : array) {
                integer = 10;
            }
            return array;
        }
        return null;
    }
}