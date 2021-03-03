package com.wangzhen.javastudy.jvm.directBuffer;

import com.wangzhen.javastudy.util.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Description: 测试直接内存
 * Datetime:    2021/3/2   下午6:45
 * Author:   王震
 */
@Slf4j
public class TestDirectBuffer {
    private static int _1G = 1024*1024*1024;
    private static int _20M = 20*1024*1024;

    /**
     * 测试直接内存的分配，用jvisumal 查看其分配区域
     * @param args
     */
    public static void main(String[] args) {

        // 分配内存
        //ByteBuffer allocate = ByteBuffer.allocate(_1G);
        // 分配直接内存
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1G);
        System.out.println("直接内存分配完毕");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        System.out.println("释放直接内存");
        byteBuffer=null;
        System.gc();
        scanner.next();


    }


    /**
     * 1.测试直接内存内存溢出OOM。 java.lang.OutOfMemoryError: Direct buffer memory
     * 2.设置虚拟机参数 -XX:MaxDirectMemorySize=500m
     */
    @Test
    public void test01() throws IOException {


        ArrayList<ByteBuffer> byteBuffers = new ArrayList<>(20);
        while (true){
            SleepUtils.second(1);
            // 每秒分配20m 的直接内存
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_20M);
            byteBuffers.add(byteBuffer);
        }


    }
}
