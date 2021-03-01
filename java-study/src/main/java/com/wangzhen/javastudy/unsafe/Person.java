package com.wangzhen.javastudy.unsafe;

/**
 * Description:
 * Datetime:    2020/11/10   4:57 下午
 * Author:   王震
 */
public class Person {
    volatile int age;
    volatile String name;

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
