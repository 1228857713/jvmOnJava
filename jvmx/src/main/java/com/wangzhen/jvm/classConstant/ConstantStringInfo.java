package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;

public class ConstantStringInfo extends ConstantInfo {
    public int stringIndex;
    public ConstantPool constantPool;
    public String stringValue;

    public ConstantStringInfo(ConstantPool constantPool,ClassReader classReader) {
        super.type=ConstantInfo.CONSTANT_String_info;
        readInfo(classReader);
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader classReader) {
        stringIndex = classReader.readNByteToInt(2);
    }

    public String getStringValue() {
        stringValue = constantPool.getConstantPoolUtf8Value(stringIndex);
        return stringValue;
    }
}
