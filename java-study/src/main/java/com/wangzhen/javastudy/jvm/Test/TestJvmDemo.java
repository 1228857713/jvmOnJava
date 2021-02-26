package com.wangzhen.javastudy.jvm.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description: 测试使用编译执行模式和 解释执行模式的jvm 执行代码上的效率的区别，默认使用编译执行模式
 *              效率会高上很多
 * desc：  -Xint 解释执行模式  271
 *        —Xcomp 编译执行模式  37
 * Datetime:    2020/12/21   下午9:32
 * Author:   王震
 */
@Slf4j
public class TestJvmDemo {
    public static String toMemoryInfo(){
        int freeMemory;
        int totalMemory;
        String calcResult = null;
        Runtime runtime = Runtime . getRuntime ();
        for(int i = 0;i<100000;i++) {
            freeMemory = (int) (runtime.freeMemory()/1024/1024);
            totalMemory = (int) (runtime.totalMemory()/1024/1024);
            calcResult = freeMemory + "M/" + totalMemory+"M";
        }
        return calcResult;
    }

    @Test
    public void test1(){
        long start = System.currentTimeMillis();
        String s = toMemoryInfo();
        System.out.println(System.currentTimeMillis()-start);
    }

}
