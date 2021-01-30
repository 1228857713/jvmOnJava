package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantIntegerInfo extends ConstantInfo {
    public int value;

    public ConstantIntegerInfo(ClassReader classReader) {
        super.type = ConstantInfo.CONSTANT_Integer_info;
        readInfo(classReader);
    }

    @Override
    void readInfo(ClassReader classReader) {
        byte [] intBytes = classReader.readNByte(4);
        value = ByteUtils.bytesToInt(intBytes);
    }

    public int getValue() {
        return value;
    }
}
