package com.wangzhen.myspring.context.resource;

import java.io.File;

/**
 * Description:
 * Datetime:    2020/11/2   11:10
 * Author:   王震
 */
public interface Resource extends InputStreamSource {
    boolean isExist();

    File getFile();
}