package com.wangzhen.myspring.aop.advisor;


import com.wangzhen.myspring.aop.pointcut.PointCut;

public interface PointCutAdvisor extends Advisor {
    PointCut getPointCutResolver();
}
