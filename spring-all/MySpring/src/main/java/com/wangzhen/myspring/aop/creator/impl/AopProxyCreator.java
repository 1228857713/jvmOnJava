package com.wangzhen.myspring.aop.creator.impl;

import com.wangzhen.myspring.aop.advisor.Advisor;
import com.wangzhen.myspring.aop.advisor.AdvisorRegistry;
import com.wangzhen.myspring.aop.creator.AopFactory;
import com.wangzhen.myspring.bean.aware.BeanFactoryAware;
import com.wangzhen.myspring.bean.factory.BeanFactory;
import com.wangzhen.myspring.bean.postprocessor.AopPostProcessor;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Datetime:    2020/11/3   8:56 下午
 * Author:   王震
 */
public class AopProxyCreator implements AopPostProcessor, BeanFactoryAware , AdvisorRegistry {
    private BeanFactory beanFactory;

    private List<Advisor> advisors;

    public AopProxyCreator() {
        advisors = new ArrayList<>();
    }

    @Override
    public Object postProcessWeaving(Object bean, String beanName) throws Exception {
        List<Advisor> matchAdvisors = new ArrayList<>();

        return null;
    }

    private List<Advisor> getMatchAdvisor(Object bean) throws Exception {
//        if(CollectionUtils.isEmpty(advisors)||bean==null){
//            return null;
//        }
        return null;

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void register(Advisor advisor) {

    }

    @Override
    public List<Advisor> getAdvisor() {
        return null;
    }
}
