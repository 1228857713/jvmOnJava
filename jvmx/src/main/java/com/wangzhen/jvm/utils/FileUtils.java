package com.wangzhen.jvm.utils;

import cn.hutool.core.io.FileUtil;
import com.wangzhen.jvm.config.Constant;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @datetime: 2021/3/11   下午7:52
 * @author: 王震
 */
public class FileUtils {
    public static String className;

    public static String findClassByName (String dstClassName) throws IOException {
        findClassByName(Constant.DEFAULT_CLASS_PACKAGE, dstClassName);
        if(className==null){
            findClassByName(Constant.DEFAULT_TEST_PACKAGE, dstClassName);
        }
        return className;

    }
    public static void  findClassByName (String dirName,String dstClassName) throws IOException {
        if(dirName==null){
            return ;
        }
        File file = new File(dirName);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File temp : files) {
                if(temp.isFile()){
                    if(temp.getName().equalsIgnoreCase(dstClassName)){
                        className = temp.getAbsolutePath();
                    }
                }else {
                     findClassByName(temp.getAbsolutePath(),dstClassName);
                }

            }
        }else {
            if(file.getName().equalsIgnoreCase(dstClassName)){
                className = file.getAbsolutePath();
            }
        }

    }
}
