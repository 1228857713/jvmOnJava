package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantClassInfo extends ConstantInfo {
    public ConstantPool constantPool;

    // 类的全限定名索引
    public int nameIndex;

    public String className;

    public ConstantClassInfo(ConstantPool constantPool,ClassReader classReader) {
        super.type=ConstantInfo.CONSTANT_Class_info;
        readInfo(classReader);
        this.constantPool = constantPool;
    }

    @Override
    void readInfo(ClassReader classReader) {
        nameIndex= ByteUtils.bytesToInt(classReader.readNByte(2));
    }

    public String getClassName() {
        ConstantUtf8Info constantUtf8Info = (ConstantUtf8Info) this.constantPool.getConstantInfos()[nameIndex];
        return constantUtf8Info.getValue();
    }


}
