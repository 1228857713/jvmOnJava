package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantFloatInfo extends ConstantInfo {
    public float value;

    public ConstantFloatInfo(ClassReader classReader) {
        super.type=ConstantInfo.CONSTANT_Float_info;
        readInfo(classReader);
    }

    @Override
    void readInfo(ClassReader classReader) {
        byte [] floatBytes = classReader.readNByte(4);
        value = ByteUtils.byteToFloat32(floatBytes);
    }

    public float getValue() {
        return value;
    }
}
