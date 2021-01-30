package com.wangzhen.myspring.demo.entity;

/**
 * Description:
 * Datetime:    2020/10/25   4:55 下午
 * Author:   王震
 */
public class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
