package com.wangzhen.javastudy.jucx.locks;



public interface ReadWriteLock {
     Lock readLock();

     Lock writeLock();
}
