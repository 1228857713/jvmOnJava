package com.wangzhen.myspring.aop.advisor.impl;


import com.wangzhen.myspring.aop.advisor.PointCutAdvisor;
import com.wangzhen.myspring.aop.pointcut.PointCut;

/**
 * @Auther: Administrator
 * @Date: 2018-12-10 15:46
 * @Description: 切点通知实现
 */
public class RegexMatchAdvisor implements PointCutAdvisor {

    private String adviceName;
    private String expression;
    private PointCut pointCut;

    public RegexMatchAdvisor(String adviceName, String expression, PointCut pointCut) {
        this.adviceName = adviceName;
        this.expression = expression;
        this.pointCut = pointCut;
    }

    @Override
    public PointCut getPointCutResolver() {
        return this.pointCut;
    }

    @Override
    public String getAdviceBeanName() {
        return this.adviceName;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }
}
