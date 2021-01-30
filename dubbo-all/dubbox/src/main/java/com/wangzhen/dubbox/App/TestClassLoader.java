package com.wangzhen.dubbox.App;

import com.wangzhen.dubbox.App.entity.Delegate;
import com.wangzhen.dubbox.App.entity.DynamicProxy;
import com.wangzhen.dubbox.App.entity.IPerson;

import com.wangzhen.dubbox.annotation.RpcReference;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * Description:
 * Datetime:    2020/11/25   9:55
 * Author:   王震
 */
public class TestClassLoader {
    public static void main2(String[] args) throws Exception {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Class<?> aClass = contextClassLoader.loadClass("com.wangzhen.dubbox.App.entity.Person");
        Object o = aClass.newInstance();
        System.out.println(o);

    }
    @Test
    public void test2() throws IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL resource = contextClassLoader.getResource("wz.txt");
        InputStream inputStream = resource.openStream();
        byte [] bytes=new byte[10];
        while (inputStream.read(bytes)!=-1){
            String str = new String(bytes,"utf-8");
            System.out.println(str);
        }
    }


    @Test
    public void test3() throws Exception {
        Class<?> aClass = TestClassLoader.class.getClassLoader().loadClass("com.wangzhen.dubbox.App.entity.Delegate");
        Delegate delegate = (Delegate) aClass.newInstance();
        Field[] declaredFields = delegate.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if(declaredField.isAnnotationPresent(RpcReference.class)){
                DynamicProxy dynamicProxy = new DynamicProxy();
                Object proxy = dynamicProxy.getProxy(declaredField.getType());
                declaredField.setAccessible(true);
                declaredField.set(delegate,proxy);
            }
        }

        delegate.sayName("wangzhen");
        delegate.sayAge(18);
        int [] a = new int [10];
//        int[] array = delegate.getArray(a);
//        for (int i : array) {
//            System.out.println(i);
//        }

        System.out.println(delegate.person);
        System.in.read();

    }


    @Test
    public void test4() throws Exception {
        DynamicProxy dynamicProxy = new DynamicProxy();
        IPerson proxy = dynamicProxy.getProxy(IPerson.class);
        proxy.sayName("wangzhen");
        proxy.sayAge(18);
        System.in.read();
    }
}