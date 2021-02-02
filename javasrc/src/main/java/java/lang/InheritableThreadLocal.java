package java.lang;

/**
 *  该类的实例在 Thread 类息息相关。
 *  Thread thread = new Thread(()->{});
 *  在创建线程的时候，查看 thread的 init() 方法其中会将父线程中所有的 InheritableThreadLocal 数据copy 到子线程中。用于父线程与子线程的数据传递。
 *
 * @see ThreadLocal
 */
public class InheritableThreadLocal<T> extends ThreadLocal<T> {
    /**
     * 拿到父线程的值后，可以在这里处理后再返回给子线程
     *
     * @param parentValue the parent thread's value
     * @return the child thread's initial value
     */
    protected T childValue(T parentValue) {
        return parentValue;
    }

    /**
     * 获取当前线程内的 inheritableThreadLocals 属性
     *
     * @param t 当前线程
     */
    ThreadLocalMap getMap(Thread t) {
        return t.inheritableThreadLocals;
    }

    /**
     * 初始化线程中的 inheritableThreadLocals 属性
     *
     * @param t          当前线程
     * @param firstValue value for the initial entry of the table.
     */
    void createMap(Thread t, T firstValue) {
        t.inheritableThreadLocals = new ThreadLocalMap(this, firstValue);
    }
}
