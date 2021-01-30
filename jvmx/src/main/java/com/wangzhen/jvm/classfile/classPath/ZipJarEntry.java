package com.wangzhen.jvm.classfile.classPath;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Author: zhangxin
 * Time: 2017/4/30 0030.
 * Desc: ZipJarEntry表示ZIP或JAR文件形式的类路径,避免和Java中的ZipEntry冲突,起名为ZipJarEntry;
 */
public class ZipJarEntry extends Entry {
    String absPath;    // E:\JavaSrc\JVMByHand\tmp\test.zip  全路径
    String zipName;     // test     压缩包名,不带 .zip 或者 jar

    public ZipJarEntry(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            absPath = dir.getParentFile().getAbsolutePath();
            zipName = dir.getName();
            //去掉结尾的.zip或者.jar 千万别碰上其它情况,要不直接异常了;
            zipName = zipName.substring(0, zipName.length() - 4);
        }
    }

    public ZipJarEntry(String path, String zipName) {
        File dir = new File(path, zipName);
        if (dir.exists()) {
            absPath = dir.getAbsolutePath();
            //去掉结尾的.zip或者.jar 千万别碰上其它情况,要不直接异常了;
            this.zipName = zipName.substring(0, zipName.length() - 4);
        }
    }

    /**
     * 从zip或者jar文件中提取class文件;
     *
     * @param className class文件的相对路径，路径之间用斜线 / 分隔，文件名有.class后缀
     */
    @Override
    byte[] readClass(String className) throws IOException {
        File file = new File(absPath);

        ZipInputStream zin = null;
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;

        ZipFile zf = new ZipFile(file);
//        ZipEntry ze = zf.getEntry(zipName + "/" + className); //如果是zip文件,获取ZipEntry的时候,直接用zipName+"/"+className
        ZipEntry ze = zf.getEntry(className); //如果是jar包,获取ZipEntry的时候,直接用className,
        if (ze == null) {
            return null;
        }
        in = new BufferedInputStream(zf.getInputStream(ze));
        out = new ByteArrayOutputStream(1024);
        int size = 0;
        byte[] temp = new byte[1024];
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }

        if (zin != null) {
            zin.closeEntry();
        }

        if (in != null) {
            in.close();
        }

        if (out != null) {
            out.close();
        }

        return out.toByteArray();
    }

    @Override
    String printClassName() {
        return absPath;
    }
}
