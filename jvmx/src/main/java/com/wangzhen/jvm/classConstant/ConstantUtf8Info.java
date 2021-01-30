package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

import java.io.IOException;

public class ConstantUtf8Info extends ConstantInfo {
    String value;

    public ConstantUtf8Info(ClassReader classReader) {
        readInfo(classReader);
    }


    @Override
    void readInfo(ClassReader classReader) {
        super.type = ConstantInfo.CONSTANT_utf8_info;
        int length = ByteUtils.bytesToInt(classReader.readUint2());
        byte [] utf8Data = classReader.readNByte(length);
        try {
            value = ByteUtils.decodeMUTF8(utf8Data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue() {
        return value;
    }
}
