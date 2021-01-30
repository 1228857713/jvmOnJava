package com.wangzhen.concurrent.jucx.locks;

public abstract class AbstractOwnableSynchronizer implements java.io.Serializable{
    private static final long serialVersionUID = 3737899427754241961L;
    /**
     * Empty constructor for use by subclasses.
     */
    protected AbstractOwnableSynchronizer() { }

    private transient Thread exclusiveOwnerThread;

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    protected final void setExclusiveOwnerThread(Thread exclusiveOwnerThread) {
        this.exclusiveOwnerThread = exclusiveOwnerThread;
    }
}
