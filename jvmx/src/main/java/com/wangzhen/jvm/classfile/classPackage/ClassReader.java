package com.wangzhen.jvm.classfile.classPackage;

import com.wangzhen.jvm.utils.ByteUtils;

public class ClassReader {
    // 存放所有字节码的文件
    byte [] data;
    // 当前读取到字节码的索引
    int index = 0;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    // 读取 u1 8个字节
    public byte[] readUint1() {
        return readNByte(1);
    }
    // 读取 u2 16个字节
    public byte[] readUint2() {
        return readNByte(2);
    }

    // 读取 u4 32个字节
    public byte[] readUint4() {
        return readNByte(4);
    }
    // 读取 u8 32个字节
    public byte[] readUint8() {
        return readNByte(8);
    }

    public int readNByteToInt(int count){
        byte[] NByte = readNByte(count);
        return ByteUtils.bytesToInt(NByte);
    }

    public byte[] readNByte(int count){
        byte[] NByte = new byte[count];
        for (int i=0;i<count;i++){
            NByte[i]=data[index++];
        }
        return NByte;
    }
}
