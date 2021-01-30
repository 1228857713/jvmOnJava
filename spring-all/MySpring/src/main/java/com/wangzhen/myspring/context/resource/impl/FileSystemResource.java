package com.wangzhen.myspring.context.resource.impl;

import com.wangzhen.myspring.context.resource.Resource;

import java.io.*;

/**
 * Description:
 * Datetime:    2020/11/2   12:55
 * Author:   王震
 */
public class FileSystemResource  implements Resource {
    private File file;

    public FileSystemResource(String path) {
        this.file = new File(path);
    }

    @Override
    public boolean isExist() {
        return file.exists();
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }
}