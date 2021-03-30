
package com.wangzhen.javastudy.reflect;


/**
 * @description: 测试Lambda
 * @datetime: 2021/3/24   下午3:31
 * @author: 王震
 */

public class LambdaTest {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {

            }
        };
        runnable.run();
    }
}
