package com.wangzhen.jvm.attribute;


import com.wangzhen.jvm.classfile.classPackage.ClassReader;

/**
 * Author: zhangxin
 * Time: 2017/5/3 0003.
 * Desc:仅起标记作用，不包含任何数据。是JDK1.1引入的，可以出现在 ClassFile、field_info和method_info结构中
 * 代表词字段或方法并不是由Java源码生成的，而是由编译器自行添加的。
 */
public class SyntheticAttribute extends AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {
        //由于没有数据,所以是空的.
    }
}
