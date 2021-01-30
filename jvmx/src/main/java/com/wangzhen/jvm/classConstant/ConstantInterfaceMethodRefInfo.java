package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;


/**
 * Author: zhangxin
 * Time: 2017/5/3 0003.
 * Desc: 接口方法引用消息
 */
public class ConstantInterfaceMethodRefInfo extends ConstantMemberRefInfo {

    public ConstantInterfaceMethodRefInfo(ConstantPool constantPool, int type,ClassReader reader) {
        super(constantPool, type,reader);
        super.type=ConstantInfo.CONSTANT_InterfaceMethodref_info;
    }
}
