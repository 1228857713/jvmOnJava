package com.wangzhen.jvm.classfile;

public class App {
    // 数组
    final static int [] a =new int [10];
    final static String s ="hello world";
    final Test test1= new Test();
    Test test2 =new Test();

    public static void main(String[] args) {
        App app = new App();
    }

    public void test1(){

    }

}
class Test{
    static final int a = 10;


}

