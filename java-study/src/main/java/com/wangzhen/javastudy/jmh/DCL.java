package com.wangzhen.javastudy.jmh;

/**
 * Description:
 * Datetime:    2020/12/23   上午11:25
 * Author:   王震
 */
public class DCL {
    public static  DCL instance;
    public static DCL getInstance(){
        if(instance == null)
            synchronized (DCL.class) {
                if (instance == null) {
                    instance = new DCL();
                }
            }
        return instance;

    }
    public void sayHello(){

    }
}
