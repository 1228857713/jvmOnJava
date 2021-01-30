package com.wangzhen.algorithm.DesignPattern.singleton.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * Datetime:    2021/1/18   下午3:59
 * Author:   王震
 */
public enum  IDGeneratorEnum {
    instance;
    AtomicInteger id = new AtomicInteger(0);
    public int getid(){
        return id.incrementAndGet();
    }
}
