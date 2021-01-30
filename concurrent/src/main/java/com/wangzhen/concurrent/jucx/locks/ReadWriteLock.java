package com.wangzhen.concurrent.jucx.locks;



public interface ReadWriteLock {
     Lock readLock();

     Lock writeLock();
}
