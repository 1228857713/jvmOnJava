package jvmTest.reference;

import java.io.IOException;

/**
 * 强软弱虚终 五种引用
 * 强引用：正常 new出来的引用都是强引用，强应用不会被回收掉，
 *         jvm 内存不够的时候直接报错，也不会回收强引用
 */
public class TestNormalReference {
    public static void main(String[] args) throws IOException {
        M m =new M();
        m = null;
        System.gc();
      //  System.out.println(m);
        //System.in.read();
    }
}
