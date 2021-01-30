package com.wangzhen.algorithm.DesignPattern.singleton.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * Datetime:    2021/1/18   下午3:54
 * Author:   王震
 */
public class IDGeneratorInnerClass {
    AtomicInteger id = new AtomicInteger(0);
    private IDGeneratorInnerClass(){
    }

    public static class  Holder{
        static  IDGeneratorInnerClass instance = new IDGeneratorInnerClass();
    }
    public static IDGeneratorInnerClass getInstance(){
        return Holder.instance;
    }

    public void getid(){
        id.incrementAndGet();
    }

}
