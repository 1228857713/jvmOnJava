package com.wangzhen.myspring.context.resource.impl;

import com.wangzhen.myspring.context.resource.Resource;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 * Datetime:    2020/11/2   12:54
 * Author:   王震
 */
public class ClasspathResource implements Resource {
    private  ClassLoader classLoader;
    private  String path;
    private Class clazz;

    public ClasspathResource(ClassLoader classLoader, String path, Class clazz) {
        this.classLoader = classLoader;
        this.path = path;
        this.clazz = clazz;
    }

    @Override
    public boolean isExist() {
        if(StringUtils.isNotBlank(path)){
            if(clazz != null){
                return clazz.getResource(path) != null;
            }else if(classLoader != null){
                return classLoader.getResource(path) != null;
            }
            return this.getClass().getResource(path) != null;
        }
        return false;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if(StringUtils.isNotBlank(path)){
            if(clazz != null){
                return clazz.getResourceAsStream(path);
            }else if(classLoader != null){
                return classLoader.getResourceAsStream(path);
            }
            return this.getClass().getResourceAsStream(path);
        }
        return null;
    }
}