package com.wangzhen.algorithm.DesignPattern.singleton.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: id生成器，单例模式懒汉式加载，使用DCL机制
 * Datetime:    2021/1/18   下午2:47
 * Author:   王震
 */
public class IDGeneratorDCL {
     static volatile IDGeneratorDCL instance;
     AtomicInteger id = new AtomicInteger(0);

     private IDGeneratorDCL(){
     }

     public IDGeneratorDCL getInstance(){
        if(instance == null){
            synchronized (IDGeneratorDCL.class){
                if(instance==null){
                    instance = new IDGeneratorDCL();
                }
            }
        }
        return instance;
     }

     public int getId(){
         return id.incrementAndGet();
     }
}
