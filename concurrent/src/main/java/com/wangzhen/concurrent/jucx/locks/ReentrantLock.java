package com.wangzhen.concurrent.jucx.locks;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class ReentrantLock implements Lock,java.io.Serializable {
    private static final long serialVersionUID = 7373984872572414699L;

    private final Sync sync;

    abstract static class Sync extends AbstractQueuedSynchronizer{
        private static final long serialVersionUID = -5179523762034025860L;
        abstract void lock();
        final boolean nonfairTryAcquire(int acquires){
            final Thread current = Thread.currentThread();
            int  c = getState();
            // 0 表示线程没有被其他线程占有
            if(c==0){
                if(compareAndSetState(0,acquires)){
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }else if (current == getExclusiveOwnerThread()){
                // 如果当前线程已经占用了该线程那么 将 state 增加
                int nextc = c+acquires;
                if(nextc<0) // overflow
                    throw new Error("Maximum lock count exceeded");
                // 因为该线程占用了锁 所以不需要cas 直接设置状态
                setState(nextc);
                return true;
            }
            return false;
        }

        protected final boolean tryRelease(int releases) {
            int c = getState() - releases;
            if(Thread.currentThread() != getExclusiveOwnerThread()){
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if(c==0){
                setExclusiveOwnerThread(null);
                free = true;
            }
            setState(c);
            return free;
        }

        protected final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        final ConditionObject newCondtion(){
            return new ConditionObject();
        }

        final Thread getOwner(){
            return getState() ==0 ? null:getExclusiveOwnerThread();
        }

        final int getHoldCount(){
            return isHeldExclusively() ? getState() :0;
        }

        final boolean isLocked(){
            return getState() ==0;
        }

        private void  readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0);
        }
    }

    static final class NonfairSync extends Sync{
        private static final long serialVersionUID = 7316153563782823691L;
        @Override
        void lock() {
            if(compareAndSetState(0,1))
                setExclusiveOwnerThread(Thread.currentThread());
            else
                accquire(1);
        }

        protected final boolean tryAcquire(int acquires){
            return nonfairTryAcquire(acquires);
        }
    }

    static final class FairSync extends Sync{
        private static final long serialVersionUID = -3000897897090466540L;
        @Override
        void lock() {
            accquire(1);
        }

        protected final boolean tryAcquire(int acquires){
            final Thread current = Thread.currentThread();
            int c = getState();
            if(c==0){
                // 公平锁 和非公平锁的区别就是 公平锁加了 hasQueuedPredecessors 这个判断
                // 表示当前如果有队列的话 那么就会 直接返回false
                if(!hasQueuedPredecessors()&& compareAndSetState(0,acquires)){
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }else if(current == getExclusiveOwnerThread()){
                int nextc = c+acquires;
                if(nextc<0){
                    throw  new Error("Maximum lock count exceeded");
                }
                setState(nextc);
                return true;

            }
            return false;
        }
    }

    public ReentrantLock(){
        // 默认是非公平锁
        sync = new NonfairSync();
    }

    public ReentrantLock(boolean fair){
        sync = fair ? new FairSync() : new NonfairSync();
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tyrLock(){
        return sync.nonfairTryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(timeout));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newConDition() {
        return sync.newCondtion();
    }

    public int getHoldCount(){
        return sync.getHoldCount();
    }

    public boolean isHeldByCurrentThread(){
        return sync.isHeldExclusively();
    }

    public boolean isLocked(){
        return sync.isLocked();
    }

    public final boolean isFair(){
        return sync instanceof FairSync;
    }

    protected Thread getOwner(){
        return sync.getOwner();
    }

    public final boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }

    public final boolean hasQueuedThread(Thread thread) {

        return sync.isQueued(thread);
    }

    public final int getQueueLength() {
        return sync.getQueueLength();
    }

    protected Collection<Thread> getQueuedThreads() {
        return sync.getQueuedThreads();
    }

    public boolean hasWaiters(Condition condition){
        if(condition == null){
            throw new NullPointerException();
        }
        if(!(condition instanceof AbstractQueuedSynchronizer.ConditionObject)){
            throw new IllegalArgumentException("not owner");
        }
       // return sync.has
        return false;
    }
}

