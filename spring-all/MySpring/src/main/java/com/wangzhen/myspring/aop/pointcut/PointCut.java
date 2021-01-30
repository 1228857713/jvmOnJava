package com.wangzhen.myspring.aop.pointcut;

import java.lang.reflect.Method;

public interface PointCut {
    boolean matchsClass(Class<?> targetClass, String expresseion) throws Exception;
    boolean matchsMethod(Class<?> targetClass, Method method, String expresseion) throws Exception;
}
