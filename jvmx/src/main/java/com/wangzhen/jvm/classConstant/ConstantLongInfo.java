package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantLongInfo extends ConstantInfo {
    public long value;

    public ConstantLongInfo(ClassReader classReader) {
        super.type=ConstantInfo.CONSTANT_Long_info;
        readInfo(classReader);
    }

    @Override
    void readInfo(ClassReader classReader) {
        byte [] logBytes = classReader.readNByte(8);
        value = ByteUtils.byteToLong64(logBytes);
    }
}
