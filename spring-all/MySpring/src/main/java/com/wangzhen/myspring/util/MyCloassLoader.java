package com.wangzhen.myspring.util;

import java.io.*;

/**
 * Description:
 * Datetime:    2020/11/2   10:22
 * Author:   王震
 */
public class MyCloassLoader extends ClassLoader {

    public void findFiles(File file) throws Exception {
        if(file == null){
            return;
        }
        File[] files = file.listFiles();
        for(File fi:files){
            if(fi.isDirectory()){
                //是目录就递归调用
                findFiles(fi);
            }else {
                //加载字节码
                Class clazz = loadClass(fi);
            }
        }
    }

    //将class文件加载为class对象
    private Class loadClass(File file) throws Exception {
        //判断文件是否合法
        boolean isClass = isClassFile(file);
        if(!isClass)
            return null;
        InputStream is = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream bos = new ByteArrayOutputStream ();
        //这里不能一次读取多个字节 new byte[1024];这样会可能导致最后多处一部分空白的字节
        int res = 0;
        while ((res = bis.read()) != -1){
            bos.write(res);
        }
        byte[] bytes = bos.toByteArray();
        String[] split = file.getName().split("\\.");
        // 将加载的 文件转换为 class 对象
        Class<?> aClass = this.defineClass(split[0], bytes, 0, bytes.length);
        return aClass;
    }
    // 纯粹只是查看 是否是 class 后缀
    // 在我看来应该 是需要判断 字节码 前 4个字节 是否是cafebabe 来判断
    private boolean isClassFile(File file) {
        if(file == null)
            return false;
        String name = file.getName();
        String[] split = name.split("\\.");
        if(split.length == 0)
            return false;
        if(split[split.length-1].equals("class"))
            return true;
        return true;
    }
}