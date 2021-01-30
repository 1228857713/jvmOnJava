package com.wangzhen.algorithm.DesignPattern.singleton.demo;

import org.junit.Test;

/**
 * Description:
 * Datetime:    2021/1/18   下午4:09
 * Author:   王震
 */
public class App {

    @Test
    public void test1(){
        IDGeneratorEnum instance = IDGeneratorEnum.instance;
        for (int i = 0; i < 100; i++) {
            System.out.println(instance.getid());
        }

    }

    @Test
    public void test2(){

    }
}
