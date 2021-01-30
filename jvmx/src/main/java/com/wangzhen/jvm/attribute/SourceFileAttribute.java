package com.wangzhen.jvm.attribute;

import com.wangzhen.jvm.classConstant.ConstantPool;
import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class SourceFileAttribute extends AttributeInfo {
    public int sourceFileIndex;
    public String sourceFileValue;
    public ConstantPool constantPool;

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public String getSourceFileValue() {
        sourceFileValue = constantPool.getConstantPoolUtf8Value(sourceFileIndex);
        return sourceFileValue;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        sourceFileIndex = ByteUtils.bytesToInt(classReader.readNByte(2));
    }
}
