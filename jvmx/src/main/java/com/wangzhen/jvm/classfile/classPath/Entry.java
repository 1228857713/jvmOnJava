package com.wangzhen.jvm.classfile.classPath;

import java.io.File;
import java.io.IOException;

/**
 * Author: zhangxin
 * Time: 2017/4/30 0030.
 * Desc:
 */
public abstract class Entry {
    //路径分隔符,在window下,使用 ; 分割开的  在Unix/Linux下使用: 分割开的
    public static final String pathListSeparator = System.getProperty("os.name").contains("Windows") ? ";" : ":";

    /**
     * 负责寻找和加载class文件
     *
     * @param className class文件的相对路径，路径之间用斜线 / 分隔，文件名有.class后缀
     */
    abstract byte[] readClass(String className) throws IOException;

    /**
     * @return 返回className的字符串表示形式;
     */
    abstract String printClassName();


    /**
     * 工厂方法,根据传入的path的形式不同,
     *
     * @param path 命令行得到的路径字符串
     * @return 创建具体的Entry
     */
    static Entry createEntry(String path) {
        if (path != null) {
            if (path.contains(pathListSeparator)) {
                return new CompositeEntry(path, pathListSeparator);
            } else if (path.contains("*")) {
                return new WildcardEntry("");
            } else if (path.contains(".jar") || path.contains(".JAR") || path.contains(".zip")
                    || path.contains("" + ".ZIP")) {
                return new ZipJarEntry(path);
            }
            return new DirEntry(path);
        } else {
            //如果命令行中没有显式的指定-cp选项,那么默认要找的class就在当前路径下
            File file = new File("");
            try {
                path = file.getCanonicalPath();
                return new DirEntry(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("illegal classpath format,or you should point out the classpath explicitly");
    }

}
