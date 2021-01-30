package com.wangzhen.myspring.aop.advice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 环绕通知
 */
public interface AroundAdvice extends Advice {
    Object around(Method method, Object[] args, Object target) throws InvocationTargetException, IllegalAccessException;
}
