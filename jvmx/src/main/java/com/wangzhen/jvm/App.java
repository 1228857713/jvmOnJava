package com.wangzhen.jvm;

public class App {
    Test test;
    static int a;

    public static void main(String[] args) {
        App app = new App();
        app.test = new Test();
        app.test.sayName();
        app.say();
    }

    public static void  say(){

    }
}
class Test{
    public void sayName(){
        System.out.println("hello world");
    }


}

