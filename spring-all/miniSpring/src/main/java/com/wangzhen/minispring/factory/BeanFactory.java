package com.wangzhen.minispring.factory;

import com.wangzhen.minispring.BeansException;

/**
 * Description:
 * Datetime:    2020/12/8   下午4:06
 * Author:   王震
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;
}
