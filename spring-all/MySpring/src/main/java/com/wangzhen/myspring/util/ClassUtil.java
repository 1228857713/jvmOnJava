package com.wangzhen.myspring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * 类操作工具类
 *
 * @author huangyong
 * @since 1.0
 */
public class ClassUtil {
    public static void main(String[] args) {
        System.out.println(ClassUtil.getClassPath());
    }

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取类路径
     * 1,这个路径是 当前运行的类的类路径形如：
     *   /Users/wangzhen/Desktop/workspace/java/jvm/jvmOnJava/springx/target/classes/
     */
    public static String getClassPath() {
        String classpath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }

    /**
     * 加载类（将自动初始化）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("加载类出错！", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 是否为 int 类型（包括 Integer 类型）
     */
    public static boolean isInt(Class<?> type) {
        return type.equals(int.class) || type.equals(Integer.class);
    }

    /**
     * 是否为 long 类型（包括 Long 类型）
     */
    public static boolean isLong(Class<?> type) {
        return type.equals(long.class) || type.equals(Long.class);
    }

    /**
     * 是否为 double 类型（包括 Double 类型）
     */
    public static boolean isDouble(Class<?> type) {
        return type.equals(double.class) || type.equals(Double.class);
    }

    /**
     * 是否为 String 类型
     */
    public static boolean isString(Class<?> type) {
        return type.equals(String.class);
    }
}
