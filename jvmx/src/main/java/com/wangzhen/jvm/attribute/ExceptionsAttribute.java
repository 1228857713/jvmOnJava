package com.wangzhen.jvm.attribute;


import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

/**
 * Author: zhangxin
 * Time: 2017/5/3 0003.
 * Desc: Exceptions是变长属性，记录方法抛出的异常表
 */

/*
Exceptions_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 number_of_exceptions;
u2 exception_index_table[number_of_exceptions];
}
*/

public class ExceptionsAttribute extends AttributeInfo {

    int[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader classReader) {
        int numberOfExceptions = ByteUtils.bytesToInt(classReader.readNByte(2));
        exceptionIndexTable =new int[numberOfExceptions];
        for (int i =0 ;i<numberOfExceptions;i++){
            exceptionIndexTable[i] = ByteUtils.bytesToInt(classReader.readNByte(2));
        }
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }


}
