package com.wangzhen.dubbox.App.entity;

import com.wangzhen.dubbox.annotation.RpcReference;

/**
 * Description:
 * Datetime:    2020/11/25   10:21
 * Author:   王震
 */
public class Delegate {
    @RpcReference
    public  IPerson person;

    public void sayName(String name){
        person.sayName(name);
    }

    public void sayAge(int age){
        person.sayAge(age);
    }

    public int [] getArray(int[] a){
        return person.getArrys(a);
    }


}