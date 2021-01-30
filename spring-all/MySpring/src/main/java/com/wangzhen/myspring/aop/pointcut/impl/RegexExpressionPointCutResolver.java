package com.wangzhen.myspring.aop.pointcut.impl;

import com.wangzhen.myspring.aop.pointcut.RegexExpreseionPointCut;

import java.lang.reflect.Method;

/**
 * Description:
 * Datetime:    2020/11/3   5:13 下午
 * Author:   王震
 */
public class RegexExpressionPointCutResolver implements RegexExpreseionPointCut {
    @Override
    public boolean matchsClass(Class<?> targetClass, String expresseion) throws Exception {
        return false;
    }

    @Override
    public boolean matchsMethod(Class<?> targetClass, Method method, String expresseion) throws Exception {
        return false;
    }
}
