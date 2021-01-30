package com.wangzhen.jvm.attribute;

import com.wangzhen.jvm.classConstant.ConstantPool;
import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;


/**
 属性的基本结构
 attribute_info {
     u2 attribute_name_index;
     u4 attribute_length;
     u1 info[attribute_length];
 }
 */
public abstract class AttributeInfo {
    // 属性名的index
    public  int attributeNameIndex;
    // 属性的字节数长度
    public  int attributeLength;


    // 属性名的值（从常量池里面查找）
    public  String attributeNameValue;

    public abstract void readInfo(ClassReader classReader);

    public static AttributeInfo  readAttributeInfo(ClassReader classReader,ConstantPool constantPool){
        //读取属性名索引
        int attributeNameIndexTemp = ByteUtils.bytesToInt(classReader.readNByte(2));
        // 读取后续字节数
        int attributeLengthTemp = ByteUtils.bytesToInt(classReader.readNByte(4));
        // 从常量池中读取属性名的值
        String attributeNameValueTemp = constantPool.getConstantPoolUtf8Value(attributeNameIndexTemp);
        AttributeInfo attributeInfo =  createAttributeInfo(attributeNameValueTemp,attributeLengthTemp,classReader,constantPool);
        attributeInfo.attributeNameIndex = attributeNameIndexTemp;
        attributeInfo.attributeNameValue = attributeNameValueTemp;
        attributeInfo.attributeLength=attributeLengthTemp;
        attributeInfo.readInfo(classReader);
        return attributeInfo;
    }

    public   static AttributeInfo[] readAttributeInfos(ClassReader classReader,ConstantPool constantPool){
        // 读取属性表的数量
        int attributesCount = ByteUtils.bytesToInt(classReader.readNByte(2));
        AttributeInfo[] attributeInfos = new AttributeInfo[attributesCount];
        for (int i=0;i<attributesCount;i++){
            attributeInfos[i] = readAttributeInfo(classReader,constantPool);
        }
        return attributeInfos;
    }

    public static AttributeInfo createAttributeInfo(String attributeNameValue,int attributeLength,ClassReader classReader,ConstantPool constantPool){

        if ("Code".equals(attributeNameValue)) {
            return new CodeAttribute(constantPool);
        } else if ("ConstantValue".equals(attributeNameValue)) {
            return new ConstantValueAttribute(constantPool);
        } else if ("Deprecated".equals(attributeNameValue)) {
            return new DeprecatedAttribute();
        } else if ("Exceptions".equals(attributeNameValue)) {
            return new ExceptionsAttribute();
        } else if ("LineNumberTable".equals(attributeNameValue)) {
            return new LineNumberTableAttribute();
        } else if ("LocalVariableTable".equals(attributeNameValue)) {
            return new LocalVariableTableAttribute();
        } else if ("SourceFile".equals(attributeNameValue)) {
            return new SourceFileAttribute(constantPool);
        } else if ("Synthetic".equals(attributeNameValue)) {
            return new SyntheticAttribute();
        } else {
            return new UnparsedAttribute(attributeNameValue, attributeLength);
        }

    }
}
