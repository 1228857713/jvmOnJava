package com.wangzhen.myspring.aop;

import com.wangzhen.myspring.aop.advice.Advice;
import com.wangzhen.myspring.aop.advisor.Advisor;
import com.wangzhen.myspring.aop.advisor.PointCutAdvisor;
import com.wangzhen.myspring.bean.factory.BeanFactory;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Datetime:    2020/11/3   5:14 下午
 * Author:   王震
 */
public class AopUtils {

    public static Object applyAdvice(Object target, Object proxy, List<Advisor> advisors, Object[] args, Method method, BeanFactory beanFactory) throws Exception {


        return null;
    }

    /**
     * 获取与方法匹配的advice
     * @param method
     * @param aClass
     * @param advisors
     * @param beanFactory
     * @return 通知列表
     */
    public static List<Advice> getMatchMethodAdvice(Method method, Class<?> aClass, List<Advisor> advisors, BeanFactory beanFactory) throws Exception {
        if(CollectionUtils.isEmpty(advisors)){
            return null;
        }
        List<Advice> advices = new ArrayList<>();
        for (Advisor advisor : advisors) {
            if(advisor instanceof PointCutAdvisor){
                PointCutAdvisor pointCutAdvisor = (PointCutAdvisor) advisor;
                boolean res = pointCutAdvisor.getPointCutResolver().matchsMethod(aClass, method, advisor.getExpression());
                if(res){
                    advices.add((Advice) beanFactory.getBean(advisor.getAdviceBeanName()));
                }

            }
        }
        return advices;
    }
}