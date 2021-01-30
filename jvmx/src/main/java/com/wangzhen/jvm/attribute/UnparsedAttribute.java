package com.wangzhen.jvm.attribute;


import com.wangzhen.jvm.classfile.classPackage.ClassReader;

/**
 * Author: zhangxin
 * Time: 2017/5/3 0003.
 * Desc: 由于精力有限，这里不可能将所有的属性都实现，只是挑重要的几个实现，其它的都直接跳过所对应的字节数即可。
 */
public class UnparsedAttribute extends AttributeInfo {
    private String attributeName;
    private int attributeLen;
    private byte[] info;

    public UnparsedAttribute(String attributeName, int attributeLen) {
        this.attributeName = attributeName;
        this.attributeLen = attributeLen;
    }

    @Override
    public  void readInfo(ClassReader classReader) {
        info = classReader.readNByte(attributeLen);
    }
}
