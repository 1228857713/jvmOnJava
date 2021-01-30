package com.wangzhen.jvm.classfile.classPath;

import java.io.*;

/**
 * Author: zhangxin
 * Time: 2017/4/30 0030.
 * Desc: 表示目录形式的类路径,这是相对来说最简单的一种了,拿到的直接就是指定的路径
 */
public class DirEntry extends Entry {
    private String absDir;

    public DirEntry(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            absDir = dir.getAbsolutePath();
        }
    }


 /*   @Override
    byte[] readClass(String className) {
        File file = new File(absDir, className);
        byte[] temp = new byte[1024];
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            out = new ByteArrayOutputStream(1024);
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }*/

    @Override
    byte[] readClass(String className) throws IOException {
        File file = new File(absDir, className);
        if (!file.exists()) {
            return null;
        }
        byte[] temp = new byte[1024];
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;

        in = new BufferedInputStream(new FileInputStream(file));
        out = new ByteArrayOutputStream(1024);
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
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
        return absDir;
    }
}
