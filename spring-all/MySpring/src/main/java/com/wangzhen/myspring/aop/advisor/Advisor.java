package com.wangzhen.myspring.aop.advisor;


/**
 * 用于，寻找对应的类和方法，来对对象进行增强。
 */
public interface Advisor {
    String getAdviceBeanName();
    String getExpression();
}
