package com.wangzhen.myspring.bean.beandefinition;

/**
 * Description: 注册bean定义
 * Datetime:    2020/11/1   6:54 下午
 * Author:   王震
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册bean定义到bean工厂
     * @param bd
     * @param beanName
     */
    void register(BeanDefinition bd, String beanName);

    boolean containsBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName);
}
