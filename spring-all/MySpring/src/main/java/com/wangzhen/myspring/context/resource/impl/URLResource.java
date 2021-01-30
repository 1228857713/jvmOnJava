package com.wangzhen.myspring.context.resource.impl;

import com.wangzhen.myspring.context.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Description:
 * Datetime:    2020/11/2   12:55
 * Author:   王震
 */
public class URLResource implements Resource {
    private URL url;

    public URLResource(String path) throws MalformedURLException {
        this.url = new URL(path);
    }

    @Override
    public boolean isExist() {
        return url != null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return url.openStream();
    }
}