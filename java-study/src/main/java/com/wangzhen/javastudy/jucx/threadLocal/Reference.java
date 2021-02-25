package com.wangzhen.javastudy.jucx.threadLocal;

//import sun.misc.Cleaner;

import java.lang.ref.ReferenceQueue;

public abstract class Reference<T> {
    private T referent;

    volatile  ReferenceQueue<? super T> queue;

    Reference next;

    transient private Reference<T> discovered;

    static private class Lock{};
    private static Lock lock = new Lock();

    private static Reference<Object> pending = null;

    private static class ReferenceHandler extends Thread{
        private static void ensureClassInitialized(Class<?> clazz){

            try {
                Class.forName(clazz.getName(),true,clazz.getClassLoader());
            } catch (ClassNotFoundException e) {
                throw (Error)new NoClassDefFoundError(e.getMessage()).initCause(e);
            }


        }
        static {
            ensureClassInitialized(InterruptedException.class);
//            ensureClassInitialized(Cleaner.class);
        }
        ReferenceHandler(ThreadGroup g,String name){
            super(g,name);
        }

        public void run() {
            while (true) {
                // 注释源码
                //tryHandlePending(true);
            }
        }



    }




}
