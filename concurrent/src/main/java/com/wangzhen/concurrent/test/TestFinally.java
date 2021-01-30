package com.wangzhen.concurrent.test;



public class TestFinally {
    public static void main(String[] args) {

        int n= 5/0;
        try{
            int i = 5/0;
        }catch (Exception e){
            System.out.println("进入 catch 模块");
            e.printStackTrace();
        }finally {
            System.out.println("进入 finally 模块");
        }
        System.out.println("game over");
    }

}
