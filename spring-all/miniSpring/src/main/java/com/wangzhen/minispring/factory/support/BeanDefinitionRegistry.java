package com.wangzhen.minispring.factory.support;

import com.wangzhen.minispring.factory.config.BeanDefinition;

/**
 * Description:
 * Datetime:    2020/12/8   下午4:46
 * Author:   王震
 */
public interface BeanDefinitionRegistry {


    /**
     * @desc 向容器中注册beanDefine
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
