package com.wangzhen.myspring.bean.factory;

import com.wangzhen.myspring.bean.postprocessor.AopPostProcessor;

import java.util.Map;

/**
 * Description: 模拟spring beanfactory
 * Datetime:    2020/11/1   8:07 下午
 * Author:   王震
 */
public interface BeanFactory {
    Object getBean(String beanName) throws Exception;

    void registerBeanPostProcessor(AopPostProcessor processor);

    String[] getBeanNameForType(Class<?> tClass);

    Map<String, Object> getBeansForType(Class<?> clazz);

    Class getType(String beanName);
}
