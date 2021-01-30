package com.wangzhen.myspring.aop.advice;

import java.lang.reflect.Method;

/**
 * 后置通知
 */
public interface AfterAdvice extends Advice {
    void after(Method method, Object[] args, Object target, Object returnVal);
}
