package jvmTest.reference;

import java.lang.ref.SoftReference;


/**
 *  软引用
 *  当jvm 虚拟机内存不足时会进行gc，如果gc后内存仍然不足那么就会回收弱引用
 *  常用的缓存就是使用软引用
 */
public class TestSoftReference {
    public static void main(String[] args) throws InterruptedException {
        SoftReference<byte[]> softReference = new SoftReference(new byte[1024*1024*10]);
        System.out.println(softReference.get());
        System.gc();
        Thread.sleep(500);
        System.out.println(softReference.get());
        byte[] bytes = new byte[1024 * 1024 * 10];
        //-Xmx20m
        System.out.println(softReference.get());
    }
}
