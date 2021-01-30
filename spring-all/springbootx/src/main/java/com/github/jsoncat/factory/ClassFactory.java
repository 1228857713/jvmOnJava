package com.github.jsoncat.factory;

import com.github.jsoncat.annotation.aop.Aspect;
import com.github.jsoncat.annotation.ioc.Component;
import com.github.jsoncat.annotation.springmvc.RestController;
import com.github.jsoncat.common.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 类加载工程
 * Datetime:    2020/11/17   10:46
 * Author:   王震
 */
public class ClassFactory {
    // 存储所有扫描出来的 classs
    static  Map<Class<? extends Annotation>, Set<Class<?>>>  classMap = new ConcurrentHashMap<>();

    public static void  loadClass(String []packagesNames){
        Set<Class<?>> RestControllerCls = ReflectionUtil.scanAnnotatedClass(packagesNames, RestController.class);
        Set<Class<?>> ComponentCls = ReflectionUtil.scanAnnotatedClass(packagesNames, Component.class);
        Set<Class<?>> AspectCls = ReflectionUtil.scanAnnotatedClass(packagesNames, Aspect.class);
        classMap.put(RestController.class,RestControllerCls);


    }
}