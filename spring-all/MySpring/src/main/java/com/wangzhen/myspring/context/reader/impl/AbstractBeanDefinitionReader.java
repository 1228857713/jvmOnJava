package com.wangzhen.myspring.context.reader.impl;

import com.wangzhen.myspring.bean.beandefinition.BeanDefinitionRegistry;
import com.wangzhen.myspring.context.reader.BeanDefinitionReader;

/**
 * Description:
 * Datetime:    2020/11/2   13:10
 * Author:   王震
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    protected BeanDefinitionRegistry registry;
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

}