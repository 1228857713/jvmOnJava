package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantMethodHandleInfo extends ConstantInfo {
    private byte referenceKind;
    private int referenceIndex;

    public ConstantMethodHandleInfo(ClassReader classReader) {
        super.type=ConstantInfo.CONSTANT_MethodHandle_info;
        readInfo(classReader);
    }

    @Override
    void readInfo(ClassReader classReader) {
        referenceKind = classReader.readNByte(1)[0];
        referenceIndex = ByteUtils.bytesToInt(classReader.readNByte(2));
    }
}
