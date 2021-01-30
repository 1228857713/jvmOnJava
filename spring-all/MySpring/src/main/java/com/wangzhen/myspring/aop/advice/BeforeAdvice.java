package com.wangzhen.myspring.aop.advice;

import java.lang.reflect.Method;


/**
 * 前置通知
 */
public interface BeforeAdvice extends Advice {
    void before(Method method, Object[] args, Object target);
}
