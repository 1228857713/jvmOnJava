package com.wangzhen.myspring.bean.aware;

import com.wangzhen.myspring.bean.factory.BeanFactory;

/**
 * Description:
 * Datetime:    2020/11/2   11:01
 * Author:   王震
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}