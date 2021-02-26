package com.wangzhen.javastudy.jvm.Test;

import org.junit.Test;

import java.util.ArrayList;

/**
 *
 * @JVMParamater: -Xms200m -Xmx200m
 */
public class ViewGC {


    /**
     * @Desc： VisuaVM 查看JVM的垃圾回收的过程（需要安装jvm 插件）
     * @JVM参数： -Xms200m -Xmx200m
     * @JVM参数： -Xms200m -Xmx200m  -XX:+UseG1GC 使用G1 垃圾回收器
     */
    @Test
    public void test1() throws  InterruptedException {
        ArrayList<byte[]> list=new ArrayList();
        while (true){
            byte [] _M=new byte[1024*1024*2];
            list.add(_M);
            Thread.sleep(1000);
        }
    }
}
