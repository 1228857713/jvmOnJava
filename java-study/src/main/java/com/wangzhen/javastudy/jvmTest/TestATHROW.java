package com.wangzhen.javastudy.jvmTest;

/**
 * Description: 异常处理相关测试类
 *              -cp jvmx/target/classes/com/wangzhen/jvm  TestATHROW
 * Datetime:    2020/10/4   3:34 下午
 * Author:   王震
 */
public class TestATHROW {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        int i = 100;
        try{
            fn();
        }catch (NullPointerException e){
            i=200;
        }
        i=300;
    }
    public static void  fn(){
        throw new IndexOutOfBoundsException();
    }

}
