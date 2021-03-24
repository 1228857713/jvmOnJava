package com.wangzhen.javastudy.reflect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: wangzhen
 * @date: 2021/3/24 13:43
 * @desc: 测试反射类
 */
@Slf4j
public class ReflectTest {
    private static int count = 1;
    public  void foo(){
        log.info("反射的fn() 方法被调用了");
        new Exception("test#"+(count++)).printStackTrace();
    }

    public  void fn(){
        log.info("反射的fn() 方法被调用了");
        new Exception("test#"+(count++)).printStackTrace();
    }


    /**
     * 1.反射方法在被调用的时候0-15 次默认是用的 native 方法调用（sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)），
     *   而第16次会使用ASM新生成一个类，以后直接调用这个类的方法即可(sun.reflect.GeneratedMethodAccessor1.invoke(Unknown Source))
     *   这个类可以通过arthas生成
     * 2.因为native的方法调用比直接调用动态代理的类慢20倍左右，而通过ASM生成class文件也是比较耗时的，所以jvm 做了一个折中方案，在第17次的时候会开始生成新的class 以便调用
     */
    @Test
    public void test01(){
        try {
            Class<?> reflectTest = Class.forName("com.wangzhen.javastudy.reflect.ReflectTest");
            Method foo = reflectTest.getDeclaredMethod("foo");
            Object obj = reflectTest.newInstance();
            for (int i = 0; i < 20; i++) {
                foo.invoke(obj);
            }
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02(){
        try {
            Class<?> reflectTest = Class.forName("com.wangzhen.javastudy.reflect.ReflectTest");
            Method foo = reflectTest.getDeclaredMethod("foo");
            Method fn = reflectTest.getDeclaredMethod("fn");
            Object obj = reflectTest.newInstance();
            for (int i = 0; i < 20; i++) {
                fn.invoke(obj);
                foo.invoke(obj);
            }
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}