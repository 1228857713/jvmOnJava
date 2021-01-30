package com.wangzhen.jvm.attribute;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;

public class DeprecatedAttribute extends AttributeInfo{
    @Override
    public void readInfo(ClassReader classReader) {
        //由于没有数据,所以是空的.
    }
}
