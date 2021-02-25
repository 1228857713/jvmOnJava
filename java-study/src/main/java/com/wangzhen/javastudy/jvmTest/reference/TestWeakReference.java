package com.wangzhen.javastudy.jvmTest.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用： 只要jvm gc 的时候 如果这个对象没有被强引用关联那么他就会被回收掉
 * 场景： weakhashMap,threadLocal 中的 threadLocalMap 的entry 的key 就是弱引用
 */
public class TestWeakReference {
    public static void main(String[] args) {
        WeakReference<M> weakReference = new WeakReference<>(new M());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
