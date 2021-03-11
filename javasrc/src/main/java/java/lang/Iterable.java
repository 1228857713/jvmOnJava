/*
 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package java.lang;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Implementing this interface allows an object to be the target of
 * the "for-each loop" statement. See
 * <strong>
 * <a href="{@docRoot}/../technotes/guides/language/foreach.html">For-each Loop</a>
 * </strong>
 *
 * @param <T> the type of elements returned by the iterator
 *
 * @since 1.5
 * @jls 14.14.2 The enhanced for statement
 */
/*
 * Iterable 可迭代的：实现类该接口表明了该类是可迭代的一个类
 * Iterator 迭代器：用于定义一个迭代类的接口
 *
 *
 * 内部迭代器，常用作容器类的接口，以支持遍历操作（同时支持流式遍历）
 * 内部迭代器的特点是嵌入式
 * 其迭代行为必须在容器对象内部实现（借助了外部比较器）
 *
 * 一个类如果实现了Iterable接口，就意味着“该类本身支持遍历”，并可以通过for-each这种循环语法来直接遍历
 * 当然，一个类如果没有实现Iterable接口，也可以通过挂载外部迭代器Iterator进行遍历
 *
 * 此外，内部迭代器还
 *
 * 注1：区别于外部迭代器Iterator
 * 注2：区别于枚举器Enumeration
 * 注3：区别于流迭代器Spliterator
 */
public interface Iterable<T> {
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    /**
     *
     * @return 返回一个迭代器
     */
    Iterator<T> iterator();

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Unless otherwise specified by the implementing class,
     * actions are performed in the order of iteration (if an iteration order
     * is specified).  Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @implSpec
     * <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @since 1.8
     */
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    /**
     * Creates a {@link Spliterator} over the elements described by this
     * {@code Iterable}.
     *
     * @implSpec
     * The default implementation creates an
     * <em><a href="Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator
     * inherits the <em>fail-fast</em> properties of the iterable's iterator.
     *
     * @implNote
     * The default implementation should usually be overridden.  The
     * spliterator returned by the default implementation has poor splitting
     * capabilities, is unsized, and does not report any spliterator
     * characteristics. Implementing classes can nearly always provide a
     * better implementation.
     *
     * @return a {@code Spliterator} over the elements described by this
     * {@code Iterable}.
     * @since 1.8
     */
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
