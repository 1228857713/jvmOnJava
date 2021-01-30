package com.wangzhen.dubbox.common.util;

/**
 * Description:
 * Datetime:    2020/12/3   15:08
 * Author:   王震
 */
public class RuntimeUtil {
    public static int cpus(){
        // 获取当前可用的线程数
        return Runtime.getRuntime().availableProcessors();
    }
}