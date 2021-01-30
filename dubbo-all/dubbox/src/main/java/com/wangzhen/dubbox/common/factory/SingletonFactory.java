package com.wangzhen.dubbox.common.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Description:
 * Datetime:    2020/11/24   16:32
 * Author:   王震
 */
public class SingletonFactory {
    private  volatile   static  HashMap<String,Object>  map = new HashMap<String ,Object>();

    public static <T> T getInstance(Class<T> clz){
        String name = clz.getName();
        Object o = map.get(name);
        if(o==null){
            synchronized (map){
                if(!map.containsKey(name)){
                    try {
                         o = clz.getDeclaredConstructor(null).newInstance();
                         map.put(name,o);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return clz.cast(o);
    }
}