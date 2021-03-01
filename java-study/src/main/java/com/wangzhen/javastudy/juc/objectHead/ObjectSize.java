package com.wangzhen.javastudy.juc.objectHead;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;


import java.nio.ByteOrder;

public class ObjectSize {
    byte i;

    public static void main(String[] args) {


    }

    @Test
    public void testENDIAN(){
        //LITTLE_ENDIAN：小端模式
        //BIG_ENDIAN：大端模式
        System.out.println("当前是：" + ByteOrder.nativeOrder());
    }

    @Test
    public void testArraySize(){
        // -XX:-UseCompressedOops
        int[] array=new int[1];
        System.out.println(ClassLayout.parseInstance(array).toPrintable());

    }

    @Test
    public void testObjectSize(){
        // -XX:+UseCompressedOops :开启指针压缩
        //-XX:-UseCompressedOops :关闭指针压缩
        // jdk8 默认开启指针压缩
        System.out.println(ClassLayout.parseInstance(new ObjectSize()).toPrintable());
    }

    @Test
    public void testClassSzie(){
         System.out.println(ClassLayout.parseClass(ObjectSize.class).toPrintable());
    }




}
