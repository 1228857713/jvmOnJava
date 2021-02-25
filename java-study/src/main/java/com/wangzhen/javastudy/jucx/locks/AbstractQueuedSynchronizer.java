package com.wangzhen.javastudy.jucx.locks;

import com.wangzhen.javastudy.jucx.util.UnsafeUtil;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements java.io.Serializable{
    private static final long serialVersionUID = 7373984972572414691L;

    protected AbstractQueuedSynchronizer(){
    }


    //
    static final class Node{
        // 共享锁 的等待状态
        static final Node SHARED = new Node();
        // 排他锁的 等待状态
        static  final Node EXCLUSIVE = null;

        // waitstatus 的状态 因为超时或者中断，节点会被设置为取消状态，被取消的节点时不会参与到竞争中的，他会⼀直保持取消状态不会转变为
        // 其他状态
        static final  int CANCELLED = 1;
        // waitstatus 后继节点的线程处于等待状态，⽽当前节点的线程如果释放了同步状态或者被取消，将会通知后继节点，使后继节点的
        // 线程得以运行
        static final  int SIGNAL = -1;

        // waitstatus 的状态 节点在等待队列中，节点线程等待在Condition上，当其他线程对Condition调⽤了signal()后，该节点将会从等待
        // 队列中转移到同步队列中，加入到同步状态的获取中
        static final int CONDITION = -2;
        // waitstatus 的状态  表示下⼀次共享式同步状态获取，将会⽆条件地传播下去
        static final int PROPAGATE = -3;

        /** 等待状态 */
        volatile  int waitStatus;
        // 前驱节点
        volatile Node prev;
        // 后继节点
        volatile Node next;

        //
        volatile Thread thread;
        // 等待队列中的后续节点。如果当前节点是共享的，那么字段将是⼀个 SHARED 常量，也就是说节点类型（独占和共享)
        // 和等待队列中的后续节点共享同一个字段
        Node nextWaiter;

        final boolean isShared(){
            return nextWaiter == SHARED;
        }

        // 获取前驱节点
        final Node prodecessor()throws NullPointerException{
            Node p = prev;
            if(p==null)
                throw new NullPointerException();
            else
                return p;
        }

        Node(){

        }

        Node(Thread thread,Node mode){
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread,int waitStatus){
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }


    private transient volatile Node head;
    private transient volatile Node tail;

    // 同步器的状态,
    // state>0 表示 已经获取了锁
    // state=0 表示释放了锁
    private volatile int state;

    protected final  int getState(){
        return this.state;
    }

    protected final void setState(int newState){
        this.state=newState;
    }

    protected final boolean compareAndSetState(int expect,int update){
        return unsafe.compareAndSwapInt(this,stateOffset,expect,update);
    }

    protected  final boolean compareAndSetHead(Node update){
        return unsafe.compareAndSwapObject(this,headOffset,null,update);
    }

    protected  final boolean compareAndSetTail(Node expect,Node update){
        return unsafe.compareAndSwapObject(this,tailOffset,expect,update);
    }

    protected static final boolean compareAndSetWaitStatus(Node node,int expect,int update){
        return unsafe.compareAndSwapInt(node,waitStatusOffset,expect,update);
    }
    /**
     * CAS next field of a node.
     */
    private static final boolean compareAndSetNext(Node node,
                                                   Node expect,
                                                   Node update) {
        return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
    }



    // 自旋时间门槛 纳秒
    static final long spinForTimeoutThreshold = 1000L;

    // 加入node 节点到双向列表中，
    // 如果列表是空的 那么会加入一个空节点，也就是说 clh 列表中必然会 大于2个节点
    private Node enq(final Node node){
        for (;;){
            Node t = tail;
            // 刚开始的时候 头结点和尾节点都是空 需要加入一个空节点
            if(t==null){
                // 给头节点设置一个node（这里其实头尾都一样）
                if(compareAndSetHead(new Node())){
                    tail=head;
                }
            }else {
                // 注意 到这个else 的时候 Node t = tail; 已经将 t 赋值了，所以这时候 t 指向的就是 当前唯一的 node 而不是空
                node.prev=t;
                if(compareAndSetTail(tail,node)){
                    t.next = node;
                   return t;
                }
            }
        }
    }

    public Node addWaiter(Node mode){
        Node node = new Node(Thread.currentThread(),mode);
        Node pred = tail;
        if(pred!=null){
            node.prev = pred;
            if(compareAndSetTail(pred,node)){
                pred.next = node;
                return node;
            }
        }
        // 将该节点 加入到 CLH 列表中，如果该节点中为空的那么先在里面加入一个节点
        // 所以有线程的节点 前面必然是有一个节点
        enq(node);
        return node;
    }

    private void setHead(Node node){
        head = node;
        node.prev =null;
        node.thread = null;
    }

    /**
     *  唤醒节点的 后继节点，如果存在的话
     * @param node 一般是 CLH 的头节点
     */
    private void unparkSuccesor(Node node){
        int ws = node.waitStatus;
        if(ws < 0){
            compareAndSetWaitStatus(node,ws,0);
        }
        // 得到该节点的后继节点
        Node s = node.next;
        if(s==null||s.waitStatus>0){
            s=null;
            for (Node t=tail;t!=null&& t!=node;t=t.prev){
                if(t.waitStatus<=0)
                    s=t;
            }
        }
        if(s!=null)
            LockSupport.unpark(s.thread);
    }

    // 暂时不实现
    private void doReleaseShared(){
    }

    // 暂时不实现
    private void setHeadAndPropagate(Node node,int propagate){

    }

    // 取消一个尝试的获取
    private void cancelAcquire(Node node){

    }

    private static boolean shouldParkAfterFailedAcquire(Node pred,Node node){
            int ws = pred.waitStatus;
            if(ws == Node.SIGNAL)
                return true;
            if(ws > 0 ){
                do{
                    node.prev = pred = pred.prev;
                }while (pred.waitStatus>0);
                pred.next = node;
            }else {
                compareAndSetWaitStatus(pred,ws, Node.SIGNAL);
            }

            return false;
    }

    static void selfInterrupt(){
        Thread.currentThread().interrupt();
    }


    private final  boolean parkAndCheckInterrupt(){
        LockSupport.park(this);
        // 会清除打断标记
        return Thread.interrupted();
    }

    final boolean acquireQueued(final Node node,int arg){
        boolean failed = true;
        try {
            boolean interrupted = false;

            //⾃旋过程，其实就是⼀个死循环⽽已
            for (;;){
                final Node p = node.prodecessor();
                // 当前线程的前驱节点是否是头节点，且同步状态为成功
                if(p==head&& tryAcquire(arg)){
                    setHead(node);
                    p.next = null; // help gc
                    failed =false;
                    return interrupted;
                }
                if(shouldParkAfterFailedAcquire(p,node)&&parkAndCheckInterrupt())
                    interrupted = true;
            }
        }finally {
            if(failed)
                cancelAcquire(node);
        }

    }

    //
    private void doAcquireInterruptibly(int arg)throws InterruptedException{

    }

    private boolean doAcquireNanos(int arg, long nanosTimeout){
        return  true;
    }

    private void doAcquireShared(int arg) {

    }

    private void doAcquireSharedInterruptibly(int arg){

    }

    private boolean doAcquireSharedNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        return true;
    }

    // 在具体的锁中实现
    protected boolean tryAcquire(int arg){
        throw new UnsupportedOperationException();
    }

    protected boolean tryRelease(int arg){
        throw new UnsupportedOperationException();
    }

    protected int tryAcquireShared(int arg){
        throw  new UnsupportedOperationException();
    }

    protected  boolean tryReleaseShared(int arg){
        throw  new UnsupportedOperationException();
    }

    protected boolean isHeldExclusively(){
        throw  new UnsupportedOperationException();
    }

    public final void accquire(int arg){
        if(!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE),arg))
            selfInterrupt();
    }

    public final void acquireInterruptibly(int arg)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        if (!tryAcquire(arg))
            doAcquireInterruptibly(arg);
    }

    public final boolean tryAcquireNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        return tryAcquire(arg) ||
                doAcquireNanos(arg, nanosTimeout);
    }

    public final boolean release(int arg){
        if(tryRelease(arg)){
            Node h =head;
            if(head!=null&&h.waitStatus!=0){
                unparkSuccesor(h);
            }
            return true;
        }
        return false;
    }

    public final void accquireShard(int arg){
        if(tryAcquireShared(arg)<0){
            doAcquireShared(arg);
        }
    }

    public final void accquireSharedInterruptibly(int arg)throws InterruptedException{
        if(Thread.interrupted())
            throw new InterruptedException();
        if(tryAcquireShared(arg)<0)
            doAcquireInterruptibly(arg);
    }

    public final boolean tryAcquireSharedNanos(int arg,long nanosTimeout) throws  InterruptedException{
        if (Thread.interrupted())
            throw new InterruptedException();
        return tryAcquireShared(arg) >= 0 ||
                doAcquireSharedNanos(arg, nanosTimeout);
    }

    public final boolean releaseShared(int arg){
        if(tryReleaseShared(arg)){
            doReleaseShared();
            return true;
        }
        return false;
    }

    public final boolean hasQueuedThreads(){
        return head!=tail;
    }
    public final boolean hasContended() {
        return head != null;
    }

    public final Thread getFirstQueuedThread() {
        // handle only fast path, else relay
        return (head == tail) ? null : fullGetFirstQueuedThread();
    }

    private Thread fullGetFirstQueuedThread() {
        Node h, s;
        Thread st;
        if (((h = head) != null && (s = h.next) != null &&
                s.prev == head && (st = s.thread) != null) ||
                ((h = head) != null && (s = h.next) != null &&
                        s.prev == head && (st = s.thread) != null))
            return st;

        /*
         * Head's next field might not have been set yet, or may have
         * been unset after setHead. So we must check to see if tail
         * is actually first node. If not, we continue on, safely
         * traversing from tail back to head to find first,
         * guaranteeing termination.
         */

        Node t = tail;
        Thread firstThread = null;
        while (t != null && t != head) {
            Thread tt = t.thread;
            if (tt != null)
                firstThread = tt;
            t = t.prev;
        }
        return firstThread;
    }

    public final boolean isQueued(Thread thread){
        if(thread == null){
            throw  new NullPointerException();
        }else {
            for (Node t = tail;t!=null;t=t.prev){
                if (t.thread==thread)
                    return true;
            }
        }
        return false;
    }

    final boolean apparentlyFirstQueuedIsExclusive() {
        Node h, s;
        return (h = head) != null &&
                (s = h.next)  != null &&
                !s.isShared()         &&
                s.thread != null;
    }

    public final boolean hasQueuedPredecessors() {
        // The correctness of this depends on head being initialized
        // before tail and on head.next being accurate if the current
        // thread is first in queue.
        Node t = tail; // Read fields in reverse initialization order
        Node h = head;
        Node s;
        return h != t &&
                ((s = h.next) == null || s.thread != Thread.currentThread());
    }

    public final int getQueueLength(){
        int num =0;
        for (Node node = tail;node!=null;node = node.prev){
            if(node.thread!=null)
                num++;
        }
        return num;
    }


    public final Collection<Thread> getQueuedThreads(){
        List<Thread> list = new ArrayList<Thread>();
        for (Node node = tail;node!=null;node = node.prev){
            Thread t = node.thread;
            if(t!=null)
                list.add(t);
        }

        return list;

    }

    public final Collection<Thread> getExclusiveQueuedThreads(){
        List<Thread> list = new ArrayList<Thread>();
        for (Node node = tail;node!=null;node = node.prev){
            if(!node.isShared()){
                Thread t = node.thread;
                if(t!=null)
                    list.add(t);
            }

        }

        return list;

    }

    public final Collection<Thread> getSharedQueuedThreads() {
        ArrayList<Thread> list = new ArrayList<Thread>();
        for (Node p = tail; p != null; p = p.prev) {
            if (p.isShared()) {
                Thread t = p.thread;
                if (t != null)
                    list.add(t);
            }
        }
        return list;
    }

    public String toString() {
        int s = getState();
        String q  = hasQueuedThreads() ? "non" : "";
        return super.toString() +
                "[State = " + s + ", " + q + "empty queue]";
    }


    final boolean isOnSyncQueue(Node node) {
        if (node.waitStatus == Node.CONDITION || node.prev == null)
            return false;
        if (node.next != null) // If has successor, it must be on queue
            return true;
        /*
         * node.prev can be non-null, but not yet on queue because
         * the CAS to place it on queue can fail. So we have to
         * traverse from tail to make sure it actually made it.  It
         * will always be near the tail in calls to this method, and
         * unless the CAS failed (which is unlikely), it will be
         * there, so we hardly ever traverse much.
         */
        return findNodeFromTail(node);
    }

    private boolean findNodeFromTail(Node node) {
        Node t = tail;
        for (;;) {
            if (t == node)
                return true;
            if (t == null)
                return false;
            t = t.prev;
        }
    }

    final boolean transferForSignal(Node node) {
        /*
         * If cannot change waitStatus, the node has been cancelled.
         */
        if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
            return false;

        /*
         * Splice onto queue and try to set waitStatus of predecessor to
         * indicate that thread is (probably) waiting. If cancelled or
         * attempt to set waitStatus fails, wake up to resync (in which
         * case the waitStatus can be transiently and harmlessly wrong).
         */
        Node p = enq(node);
        int ws = p.waitStatus;
        if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
            LockSupport.unpark(node.thread);
        return true;
    }

    final boolean transferAfterCancelledWait(Node node) {
        if (compareAndSetWaitStatus(node, Node.CONDITION, 0)) {
            enq(node);
            return true;
        }
        /*
         * If we lost out to a signal(), then we can't proceed
         * until it finishes its enq().  Cancelling during an
         * incomplete transfer is both rare and transient, so just
         * spin.
         */
        while (!isOnSyncQueue(node))
            Thread.yield();
        return false;
    }

    final int fullyRelease(Node node) {
        boolean failed = true;
        try {
            int savedState = getState();
            if (release(savedState)) {
                failed = false;
                return savedState;
            } else {
                throw new IllegalMonitorStateException();
            }
        } finally {
            if (failed)
                node.waitStatus = Node.CANCELLED;
        }
    }

    public final boolean owns(ConditionObject condition){
        return false;
    }
    public  final boolean hasWaiters(ConditionObject condition){
        return false;
    }

    public final int getWaitQueueLength(ConditionObject condition) {
        return 0;
    }

    public final Collection<Thread> getWaitingThreads(ConditionObject condition) {
        return null;
    }

    public class ConditionObject implements Condition,java.io.Serializable {
        private static final long serialVersionUID = 1173984872572414699L;

        private transient Node firstWaiter;
        private transient Node lastWaiter;

        /** Mode meaning to reinterrupt on exit from wait */
        private static final int REINTERRUPT =  1;
        /** Mode meaning to throw InterruptedException on exit from wait */
        private static final int THROW_IE    = -1;

        public ConditionObject(){

        }
        private Node addConditionWaiter() {

            return null;
        }

        private void doSignal(Node first){

        }

        private void doSignalAll(Node first){

        }

        private void unlinkCancelledWaiters() {

        }


        @Override
        public void await() throws InterruptedException {

        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {

        }

        @Override
        public void signalAll() {

        }

        private int checkInterruptWhileWaiting(Node node) {
            return Thread.interrupted() ?
                    (transferAfterCancelledWait(node) ? THROW_IE : REINTERRUPT) :
                    0;
        }

        private void reportInterruptAfterWait(int interruptMode)
                throws InterruptedException {
            if (interruptMode == THROW_IE)
                throw new InterruptedException();
            else if (interruptMode == REINTERRUPT)
                selfInterrupt();
        }

        final boolean isOwnedBy(AbstractQueuedSynchronizer sync){
            return sync == AbstractQueuedSynchronizer.this;
        }

        protected final boolean hasWaiters() {
            if (!isHeldExclusively())
                throw new IllegalMonitorStateException();
            for (Node w = firstWaiter; w != null; w = w.nextWaiter) {
                if (w.waitStatus == Node.CONDITION)
                    return true;
            }
            return false;
        }

        protected final int getWaitQueueLength() {
            if (!isHeldExclusively())
                throw new IllegalMonitorStateException();
            int n = 0;
            for (Node w = firstWaiter; w != null; w = w.nextWaiter) {
                if (w.waitStatus == Node.CONDITION)
                    ++n;
            }
            return n;
        }

        protected final Collection<Thread> getWaitingThreads() {
            if (!isHeldExclusively())
                throw new IllegalMonitorStateException();
            ArrayList<Thread> list = new ArrayList<Thread>();
            for (Node w = firstWaiter; w != null; w = w.nextWaiter) {
                if (w.waitStatus == Node.CONDITION) {
                    Thread t = w.thread;
                    if (t != null)
                        list.add(t);
                }
            }
            return list;
        }
    }






    private static final Unsafe unsafe = UnsafeUtil.getUnsfe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    private static final long nextOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset(Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset(Node.class.getDeclaredField("next"));

        }catch (Exception e){
            throw new Error(e);
        }
    }



}
