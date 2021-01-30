package com.wangzhen.dubbox.common.factory;

/**
 * Description:
 * Datetime:    2020/11/24   16:34
 * Author:   王震
 */
public class SingleFacory {
    private static volatile Object instance;
    public static Object getIntance(){
            if (instance == null) {
                synchronized (SingleFacory.class){
                    if(instance ==null)
                       instance = new Object();
                }
            }

        return instance;
    }
}