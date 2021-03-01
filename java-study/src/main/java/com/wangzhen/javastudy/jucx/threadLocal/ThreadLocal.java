package com.wangzhen.javastudy.jucx.threadLocal;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @param <T>
 */
public class ThreadLocal<T> {
    private final int threadLocalHashCode = nextHashCode();

    private static AtomicInteger nextHashCode = new AtomicInteger();

    /**
     * Returns the next hash code.
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }


    private static final int HASH_INCREMENT = 0x61c88647;


    // protected 是子类不同包也可以访问
    // default 是子类不同包不可以访问
    protected T initialValue(){
        return null;
    }

    public ThreadLocal() {
    }

    public T get(){
        Thread thread = Thread.currentThread();
        ThreadLocal.ThreadLocalMap map = getMap(thread);
        ThreadLocalMap.Entry entry =map.getEntry(this);
        T t = (T) entry.value;

        return t;
    }

    public void set(T value){
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if(map!=null){
            map.set(this,value);
        }else {
            createMap(t,value);
        }
    }

    void createMap(Thread t, T firstValue) {
      //  t.threadLocals = new ThreadLocalMap(this, firstValue);
    }
    ThreadLocalMap getMap(Thread t) {
        // 直接返回 线程的 本地属性
        return null;
    }

    static class ThreadLocalMap{

        static class Entry extends WeakReference<ThreadLocal<?>> {
            Object value;

            public Entry(ThreadLocal<?> referent, Object v) {
                super(referent);
                value = v;
            }
        }

        public static final int INITAL_CAPACITY = 16 ;
        private Entry [] table;
        private int size = 0;

        private int threshold;

        private void setThreshold(int len){
            threshold = len*2/3;
        }

        /**
         * Increment i modulo len.
         */
        private static int nextIndex(int i, int len) {
            return ((i + 1 < len) ? i + 1 : 0);
        }

        /**
         * Decrement i modulo len.
         */
        private static int prevIndex(int i, int len) {
            return ((i - 1 >= 0) ? i - 1 : len - 1);
        }

        ThreadLocalMap(ThreadLocal<?> firstkey, Object firstValue){
            table = new Entry[INITAL_CAPACITY];
            int i = firstkey.threadLocalHashCode & (INITAL_CAPACITY -1);
            table [i] = new Entry(firstkey,firstValue);
            size =1 ;
            setThreshold(INITAL_CAPACITY);
        }

        private ThreadLocalMap(ThreadLocalMap parentMap){
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            table = new Entry[len];
            for (int j = 0; j < len; j++) {
                Entry e = parentTable[j];
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
                    if (key != null) {
                       // Object value = key.childValue(e.value);
                        Object value = null;
                        Entry c = new Entry(key, value);
                        int h = key.threadLocalHashCode & (len - 1);
                        while (table[h] != null)
                            h = nextIndex(h, len);
                        table[h] = c;
                        size++;
                    }
                }
            }
        }

        private Entry getEntry(ThreadLocal<?> key){
            int i = key.threadLocalHashCode &(table.length -1 );
            Entry e = table[i];
            if(e!=null && e.get() == key)
                return e;
            return null;
        }

        private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e){
            Entry[] tab = table;
            int len = tab.length;
            while (e!=null){
                ThreadLocal<?> k = e.get();
                if(k == key)
                    return e;
                if (k==null){
                    expungeStaleEntry(i);
                }else {
                    i = nextIndex(i,len);
                }
                e=tab[i];
            }
            return null;
        }

        private void replaceStaleEntry(ThreadLocal<?> key, Object value,
                                       int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            Entry e;

            // Back up to check for prior stale entry in current run.
            // We clean out whole runs at a time to avoid continual
            // incremental rehashing due to garbage collector freeing
            // up refs in bunches (i.e., whenever the collector runs).
            int slotToExpunge = staleSlot;
            for (int i = prevIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = prevIndex(i, len))
                if (e.get() == null)
                    slotToExpunge = i;

            // Find either the key or trailing null slot of run, whichever
            // occurs first
            for (int i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();

                // If we find key, then we need to swap it
                // with the stale entry to maintain hash table order.
                // The newly stale slot, or any other stale slot
                // encountered above it, can then be sent to expungeStaleEntry
                // to remove or rehash all of the other entries in run.
                if (k == key) {
                    e.value = value;

                    tab[i] = tab[staleSlot];
                    tab[staleSlot] = e;

                    // Start expunge at preceding stale entry if it exists
                    if (slotToExpunge == staleSlot)
                        slotToExpunge = i;
                    cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                    return;
                }

                // If we didn't find stale entry on backward scan, the
                // first stale entry seen while scanning for key is the
                // first still present in the run.
                if (k == null && slotToExpunge == staleSlot)
                    slotToExpunge = i;
            }

            // If key not found, put new entry in stale slot
            tab[staleSlot].value = null;
            tab[staleSlot] = new Entry(key, value);

            // If there are any other stale entries in run, expunge them
            if (slotToExpunge != staleSlot)
                cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
        }

        private boolean cleanSomeSlots(int i, int n) {
            boolean removed = false;
            Entry[] tab = table;
            int len = tab.length;
            do {
                i = nextIndex(i, len);
                Entry e = tab[i];
                if (e != null && e.get() == null) {
                    n = len;
                    removed = true;
                    i = expungeStaleEntry(i);
                }
            } while ( (n >>>= 1) != 0);
            return removed;
        }

        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;

            // expunge entry at staleSlot
            tab[staleSlot].value = null;
            tab[staleSlot] = null;
            size--;

            // Rehash until we encounter null
            Entry e;
            int i;
            for (i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();
                if (k == null) {
                    e.value = null;
                    tab[i] = null;
                    size--;
                } else {
                    int h = k.threadLocalHashCode & (len - 1);
                    if (h != i) {
                        tab[i] = null;

                        // Unlike Knuth 6.4 Algorithm R, we must scan until
                        // null because multiple entries could have been stale.
                        while (tab[h] != null)
                            h = nextIndex(h, len);
                        tab[h] = e;
                    }
                }
            }
            return i;
        }

        private void set(ThreadLocal<?> key, Object value){
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len -1 );
            //tab[i] = new Entry(key,value);
            for (Entry e =tab[i];e!=null;e=tab[i=nextIndex(i,len)]){
                ThreadLocal<?> k = e.get();
                if(k==key){
                    e.value=value;
                }
                if(key ==null){
                    replaceStaleEntry(key,value,i);
                    return;
                }
            }
            tab[i] = new Entry(key,value);
            int sz = ++size;
            if (!cleanSomeSlots(i, sz) && sz >= threshold)
                rehash();


        }

        private void rehash() {
            expungeStaleEntries();

            // Use lower threshold for doubling to avoid hysteresis
            if (size >= threshold - threshold / 4)
                resize();
        }
        /**
         * Expunge all stale entries in the table.
         */
        private void expungeStaleEntries() {
            Entry[] tab = table;
            int len = tab.length;
            for (int j = 0; j < len; j++) {
                Entry e = tab[j];
                if (e != null && e.get() == null)
                    expungeStaleEntry(j);
            }
        }

        /**
         * Double the capacity of the table.
         */
        private void resize() {
            Entry[] oldTab = table;
            int oldLen = oldTab.length;
            int newLen = oldLen * 2;
            Entry[] newTab = new Entry[newLen];
            int count = 0;

            for (int j = 0; j < oldLen; ++j) {
                Entry e = oldTab[j];
                if (e != null) {
                    ThreadLocal<?> k = e.get();
                    if (k == null) {
                        e.value = null; // Help the GC
                    } else {
                        int h = k.threadLocalHashCode & (newLen - 1);
                        while (newTab[h] != null)
                            h = nextIndex(h, newLen);
                        newTab[h] = e;
                        count++;
                    }
                }
            }

            setThreshold(newLen);
            size = count;
            table = newTab;
        }



    }
}
