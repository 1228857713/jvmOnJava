package com.wangzhen.minispring.factory.config;

/**
 * Description:
 * Datetime:    2020/12/8   下午4:17
 * Author:   王震
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
