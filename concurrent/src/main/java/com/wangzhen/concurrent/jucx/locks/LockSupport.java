package com.wangzhen.concurrent.jucx.locks;


import com.wangzhen.concurrent.jucx.util.UnsafeUtil;

public class LockSupport {
    private LockSupport(){};
    private static void setBlocker(Thread t,Object arg){
        UNSAFE.putObject(t,parkBlockerOffset,arg);
    }

    public static void unpark(Thread thread){
        if(thread!=null){
            UNSAFE.unpark(thread);
        }
    }

    public static void park(Object blocker){
        Thread t = Thread.currentThread();
        setBlocker(t,blocker);
        UNSAFE.park(false,0L);
        setBlocker(t,null);

    }

    public static void parkNanos(Object blocker, long nanos) {
        if (nanos > 0) {
            Thread t = Thread.currentThread();
            setBlocker(t, blocker);
            UNSAFE.park(false, nanos);
            setBlocker(t, null);
        }
    }

    public static void parkUntil(Object blocker, long deadline) {
        Thread t = Thread.currentThread();
        setBlocker(t, blocker);
        UNSAFE.park(true, deadline);
        setBlocker(t, null);
    }

    public static Object getBlocker(Thread t) {
        if (t == null)
            throw new NullPointerException();
        return UNSAFE.getObjectVolatile(t, parkBlockerOffset);
    }

    public static void park() {
        UNSAFE.park(false, 0L);
    }

    public static void parkNanos(long nanos) {
        if (nanos > 0)
            UNSAFE.park(false, nanos);
    }

    public static void parkUntil(long deadline) {
        UNSAFE.park(true, deadline);
    }

    static final int nextSecondarySeed() {
        int r;
        Thread t = Thread.currentThread();
        if ((r = UNSAFE.getInt(t, SECONDARY)) != 0) {
            r ^= r << 13;   // xorshift
            r ^= r >>> 17;
            r ^= r << 5;
        }
        else if ((r = java.util.concurrent.ThreadLocalRandom.current().nextInt()) == 0)
            r = 1; // avoid zero
        UNSAFE.putInt(t, SECONDARY, r);
        return r;
    }
















    // Hotspot implementation via intrinsics API
    private static final sun.misc.Unsafe UNSAFE;
    public static final long parkBlockerOffset;
    // 种子
    public static final long SEED;
    // 探针，调查
    public static final long PROBE;
    // 副手 次要的
    public static final long SECONDARY;

    static {
        try {
        // 这里需要通过反射去拿
        // jvm 虚拟机默认了这个类只能 sun 的class 能用
        UNSAFE = UnsafeUtil.getUnsfe();
        Class<?> tk = Thread.class;

            parkBlockerOffset = UNSAFE.objectFieldOffset(tk.getDeclaredField("parkBlocker"));
            SEED =UNSAFE.objectFieldOffset(tk.getDeclaredField("threadLocalRandomSeed"));
            PROBE = UNSAFE.objectFieldOffset(tk.getDeclaredField("threadLocalRandomSecondarySeed"));
            SECONDARY = UNSAFE.objectFieldOffset(tk.getDeclaredField("threadLocalRandomSecondarySeed"));
        } catch (Exception e) {
            throw  new Error(e);
        }
    }
}
