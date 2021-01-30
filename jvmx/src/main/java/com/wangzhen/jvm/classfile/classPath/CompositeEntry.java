package com.wangzhen.jvm.classfile.classPath;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Author: zhangxin
 * Time: 2017/4/30 0030.
 * Desc: CompositeEntry由众多的Entry组成，正好可以表示成 Entry list;
 * 构造函数把参数（路径列表）按分隔符分成小路径，然后把每个小路径都转换成具体的 Entry实例
 */
public class CompositeEntry extends Entry {
    //不用担心,list中的entry是按照父类来转入的,在真正执行的时候,是按照各自的实际类型执行readClass()方法
    ArrayList<Entry> compositeEntries;
    private String pathList;

    public CompositeEntry() {
    }

    public CompositeEntry(String pathList, String pathListSeparator) {
        this.pathList = pathList;
        String[] paths = pathList.split(pathListSeparator);
        compositeEntries = new ArrayList<Entry>(paths.length);
        for (int i = 0; i < paths.length; i++) {
            compositeEntries.add(new DirEntry(paths[i]));
        }
    }

    @Override
    byte[] readClass(String className) {
        byte[] data;
        for (int i = 0; i < compositeEntries.size(); i++) {
            try {
                data = compositeEntries.get(i).readClass(className);
                if (data != null) {
                    return data;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    String printClassName() {
        return pathList;
    }
}
