package com.wangzhen.minispring.factory.config;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * Datetime:    2020/12/8   下午4:17
 * Author:   王震
 */

public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
