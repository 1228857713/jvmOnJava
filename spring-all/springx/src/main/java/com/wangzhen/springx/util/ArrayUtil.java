package com.wangzhen.springx.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * 数组操作工具类
 *
 * @author huangyong
 * @since 1.0
 */
public class ArrayUtil {

    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 连接数组
     */
    public static Object[] concat(Object[] array1, Object[] array2) {
        return ArrayUtils.addAll(array1, array2);
    }

    /**
     * 判断对象是否在数组中
     */
    public static <T> boolean contains(T[] array, T obj) {
        return ArrayUtils.contains(array, obj);
    }
}
