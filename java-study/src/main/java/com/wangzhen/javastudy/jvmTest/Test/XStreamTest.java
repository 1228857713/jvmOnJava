package com.wangzhen.javastudy.jvmTest.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Description:
 * Datetime:    2020/12/22   下午7:48
 * Author:   王震
 */
@Slf4j
public class XStreamTest {

    //-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xms512m -Xmx512m -Xmn100m  -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags
    //-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xms512m -Xmx512m -Xmn100m   -XX:+PrintCommandLineFlags

    @Test
    public void test1() throws FileNotFoundException, InterruptedException {
        while (true){
            Thread.sleep(1000);
            HashMap map = new HashMap<>();
            map.toString();
            map=null;
        }
    }
}
