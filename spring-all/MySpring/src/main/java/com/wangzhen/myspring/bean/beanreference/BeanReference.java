package com.wangzhen.myspring.bean.beanreference;

/**
 * Description: 包装属性依赖中的引用类型
 * Datetime:    2020/11/1   8:06 下午
 * Author:   王震
 */
public class BeanReference {
    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
