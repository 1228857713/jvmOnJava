package com.wangzhen.jvm.instructions.references;



/**
 * Description:
 * Datetime:    2021/3/11   下午3:42
 * Author:   王震
 */
public class ATHROWTest {
    public static void main(String[] args) {
        int i = func();
        System.out.println(i);
    }


    public static int func(){
        int i =100;
        try {
            func2();
        }catch (IndexOutOfBoundsException e){
            i = 200;
        }finally {
            i =300;
        }
        i=400;
        return i;
    }
    public static void func2(){
        throw new IndexOutOfBoundsException();
    }
}
