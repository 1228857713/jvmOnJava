package com.wangzhen.javastudy.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * @author: wangzhen
 * @Date: 2021/3/3 9:48
 * @Desc:
*/
@Slf4j
public class JvmTest {


    @Test
    public void test01() throws InterruptedException {
        String s = "";
        while (true){
            Thread.sleep(1);
            s += "1";
        }
    }

    public void test02(){

    }

}