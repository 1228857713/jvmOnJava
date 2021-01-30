package com.wangzhen.myspring.context.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 * Datetime:    2020/11/2   11:09
 * Author:   王震
 */
public interface InputStreamSource {
    InputStream getInputStream() throws IOException;
}