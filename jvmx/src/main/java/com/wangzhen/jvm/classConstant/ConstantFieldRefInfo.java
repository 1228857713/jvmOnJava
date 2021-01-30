package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantFieldRefInfo extends ConstantMemberRefInfo {

    public ConstantFieldRefInfo(ConstantPool constantPool, int type,ClassReader reader) {

        super(constantPool, type,reader);
        super.type = ConstantInfo.CONSTANT_Fieldref_info;
    }
}
