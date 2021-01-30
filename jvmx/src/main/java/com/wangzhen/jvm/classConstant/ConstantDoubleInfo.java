package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantDoubleInfo extends ConstantInfo {
    public Double value;

    public ConstantDoubleInfo(ClassReader classReader) {
        super.type=ConstantInfo.CONSTANT_Double_info;
        readInfo(classReader);
    }

    @Override
    void readInfo(ClassReader classReader) {
        byte [] doubleBytes = classReader.readNByte(8);
        value = ByteUtils.byteToDouble64(doubleBytes);
    }

    public Double getValue() {
        return value;
    }
}
