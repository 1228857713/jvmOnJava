package com.wangzhen.jvm.attribute;

import com.wangzhen.jvm.classConstant.ConstantPool;
import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;


/**
 * ConstantValue_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 constantvalue_index;
 * }
 */
public class ConstantValueAttribute extends AttributeInfo{
    ConstantPool constantPool;
    int constantvalueIndex;
    String constantvalueIndexVaule;

    public ConstantValueAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        constantvalueIndex = ByteUtils.bytesToInt(classReader.readNByte(2));

    }

    public int getConstantvalueIndex() {
        return constantvalueIndex;
    }
}
