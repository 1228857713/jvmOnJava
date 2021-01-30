package com.wangzhen.myspring.aop.advisor;

import java.util.List;

public interface AdvisorRegistry {

    void register(Advisor advisor);

    List<Advisor> getAdvisor();
}
