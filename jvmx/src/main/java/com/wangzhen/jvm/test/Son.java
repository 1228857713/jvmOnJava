package com.wangzhen.jvm.test;

public class Son extends Person{
    public Son(String name) {
        super(name);
    }

    @Override
    public void sayName() {
        System.out.println("I am son");
    }
}
