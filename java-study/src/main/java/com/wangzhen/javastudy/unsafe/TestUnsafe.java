package com.wangzhen.javastudy.unsafe;

import org.junit.Test;
import sun.misc.Unsafe;


// undafe 对象的获取
public class TestUnsafe {
    Unsafe unsafe = UnsafeUtils.getUnsafe();

    @Test
    public void test01() throws NoSuchFieldException {
        Person p = new Person();
        long ageOffset = unsafe.objectFieldOffset(Person.class.getDeclaredField("age"));
        long nameOffset = unsafe.objectFieldOffset(Person.class.getDeclaredField("name"));
        // compareAndSwapInt()
        // 1.操作的对象，参数的偏移量，原来的值，需要更新的值
        unsafe.compareAndSwapInt(p,ageOffset,0,26);
        unsafe.compareAndSwapObject(p,nameOffset,null,"王震");
        System.out.println(p.toString());

    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {



    }
}
