package com.wangzhen.javastudy.jvm.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description: 测试jvm 的最大堆内存和最小堆内存
 * Datetime:    2021/1/25   下午6:44
 * Author:   王震
 */
@Slf4j
public class TestJVMMemory {

    /**
     * 按照书本所说，jvm 堆内存默认初始化大小为 1/64，最大堆内存大小为物理内存的 1/4
     */
    @Test
    public void test1(){
        long initMemory = Runtime.getRuntime().totalMemory()/1024/1024;
        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;
        log.info("初始化堆内存:{}",initMemory);
        log.info("最大堆内存:{}",maxMemory);
    }
}
