package com.wangzhen.jvm.classfile.classPath;

import java.io.File;
import java.io.IOException;

public class ClassPath {
    // jre 路径
    private String jreDir;
    //分别存放三种类路径
    private Entry bootClasspath;
    private Entry extClasspath;
    private Entry userClasspath;
    //parse()函数使用 -Xjre 选项解析启动类路径和扩展类路径
    // 使用-classpath/-cp选项解析用户类路径
    //以此来初始化成员变量的三种路径
    public ClassPath(String jreOption, String cpOption) {
        jreDir = getJreDir(jreOption);
        bootClasspath = parseBootClasspath();
        extClasspath = parseExtClasspath();
        userClasspath = parseUserClasspath(cpOption);
    }


    private Entry parseBootClasspath() {
        //可能出现的情况是: jre/lib/*
        String jreLibPath = jreDir + File.separator + "lib" + File.separator + "*";
        return new WildcardEntry(jreLibPath);
    }

    private Entry parseExtClasspath() {
        //可能出现的情况是: jre/lib/ext/*
        String jreExtPath = jreDir + File.separator + "lib" + File.separator + "ext" + File.separator + "*";
        return new WildcardEntry(jreExtPath);
    }

    //确定传进来的jre的路径是否有效；
    private String getJreDir(String jreOption) {
        File jreFile;
        if (jreOption != null && !"".equals(jreOption)) {
            jreFile = new File(jreOption);
            if (jreFile.exists()) {
                return jreOption;
            }
        }

        //jreOption选项为空，那么在当前路径找
        jreFile = new File("jre");
        if (jreFile.exists()) {
            return jreFile.getAbsolutePath();
        }

        //在JAVA_HOME中找
        String java_home = System.getenv("JAVA_HOME");
       // String java_home = "/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home";
        if (java_home != null) {
            return java_home + File.separator + "jre";
        }

        throw new RuntimeException("Can not find jre folder!");
    }

    private Entry parseUserClasspath(String cpOption) {
        return Entry.createEntry(cpOption);
    }

    /***
     * ClassPath 对外的统一接口,实例化ClassPath时传入 userPath 路径和类名就可以读取字节码文件
     * 读取className 对应的字节码,注意顺序,我们的查找次序是:
     * bootClasspath => extClasspath => userClasspath;
     * @param className
     * @return
     */
    public byte[] readClass(String className) {
        //注意，用命令行加载java文件时，只写文件名，所有这里统一为文件名后补上“.class”的后缀；
        if (className.endsWith(".class")) {
            throw new RuntimeException("can't find or can't load the class: " + className);
        }
        className = className.replace(".", "/");
        className = className + ".class";
        byte[] data;
        try {
            //
            data = bootClasspath.readClass(className);
            if (data != null) {
                return data;
            }

            data = extClasspath.readClass(className);
            if (data != null) {
                return data;
            }

            data = userClasspath.readClass(className);
            if (data != null) {
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("can't find class!");
    }

    @Override
    public String toString() {
        return userClasspath.printClassName();
    }


}
