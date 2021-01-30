package com.wangzhen.myspring.bean.postprocessor;

/**
 * Description: Aop后置处理器,并不完全是为了动态代理对目标类进行增强，可能还有其他作用
 * Datetime:    2020/11/1   8:18 下午
 * Author:   王震
 */
public interface AopPostProcessor {
    Object postProcessWeaving(Object bean, String beanName) throws Exception;
}
