package com.wangzhen.jvmTest.Test;

/**
 * Description: 引用计数法测试
 * Datetime:    2020/10/21   5:03 下午
 * Author:   王震
 * add : 运行需要添加 -XX:+PrintGCDetails 参数方便查看GC 日志
 */
public class ReferenceCountGC {
    public Object instance = null;
    // 1m 内存
    private static final int _1M = 1025*1024;

    // 存储2m的数据 ，方便垃圾回收看到的gc 日志比较明显
    private byte[] bigSize = new byte[2*_1M];

    public static void main(String[] args) {
        ReferenceCountGC a = new ReferenceCountGC();
        ReferenceCountGC b  = new ReferenceCountGC();
        a.instance = null;
        b.instance = null;
        // 开始gc 看a 和b 是否会被gc
        System.gc();
    }



}
