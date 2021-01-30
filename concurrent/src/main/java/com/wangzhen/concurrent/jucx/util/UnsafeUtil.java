package com.wangzhen.concurrent.jucx.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {

    public static Unsafe getUnsfe()  {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
