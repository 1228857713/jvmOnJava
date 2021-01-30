package com.wangzhen.jvm.classConstant;


import com.wangzhen.jvm.classfile.classPackage.ClassReader;

public class ConstantMethodRefInfo extends ConstantMemberRefInfo {


    public ConstantMethodRefInfo(ConstantPool constantPool, int type, ClassReader reader) {
        super(constantPool, type,reader);
        super.type=ConstantInfo.CONSTANT_Methodref_info;
    }
}
