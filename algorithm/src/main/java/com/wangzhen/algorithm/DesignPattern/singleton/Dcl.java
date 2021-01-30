package com.wangzhen.algorithm.DesignPattern.singleton;

import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * @Desc: 关于单例一个很不错的 说明https://time.geekbang.org/column/article/194035
 */
public class Dcl {
    private Dcl(){
    }
    private static volatile Dcl instance;
    public static Dcl getInstance(){
        Dcl localRef = instance;
        if(instance==null){
            synchronized (Dcl.class){
                localRef = instance;
                if(localRef==null){
                    localRef = instance = new Dcl();
                }
            }
        }
        return localRef;
    }
}
