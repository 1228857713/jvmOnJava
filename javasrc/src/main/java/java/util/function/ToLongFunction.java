/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
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
package java.util.function;

/**
 * Represents a function that produces a long-valued result.  This is the
 * {@code long}-producing primitive specialization for {@link Function}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #applyAsLong(Object)}.
 *
 * @param <T> the type of the input to the function
 *
 * @see Function
 * @since 1.8
 */
@FunctionalInterface
public interface ToLongFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    long applyAsLong(T value);
}
