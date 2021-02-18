package com.wangzhen.jvmTest.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description:
 * Datetime:    2020/12/21   下午9:16
 * Author:   王震
 */

@Slf4j
public class TestStringintern {

    @Test
    public void test1(){
        String s2=new String("1");
        s2.intern();
        String s1="1";


        System.out.println(s1==s2);

        String s3 = new String("1")+new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3==s4);
    }
}
