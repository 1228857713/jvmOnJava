package com.wangzhen.jvm.attribute;


import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

/**
 * Author: zhangxin
 * Time: 2017/5/3 0003.
 * Desc: LineNumberTable属性表存放方法的行号信息,和前面介绍的SourceFile属性都属于调试信息，都不是运行时必需
 * 在使用javac编译器编译Java程序时，默认会在class文件中生成这些信息。可以使用javac提供的-g：none选项来关闭这些信息的生成
 * 描述Java源码行号与字节码行号之间的对应关系。
 */
public class LineNumberTableAttribute extends AttributeInfo {
    LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader classReader) {
        int lineNumberTableLength = ByteUtils.bytesToInt(classReader.readNByte(2));
        this.lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberTableEntry(ByteUtils.bytesToInt(classReader.readNByte(2)), ByteUtils.bytesToInt(classReader.readNByte(2)));
        }
    }

    /* 根据字节码中的行号,寻找其在源代码中的行号;一般情况下;多个字节码的行号可能会对应一个源文件中的一行
    0 - 15
    8 - 17
    14 - 21
    17 - 18
    18 - 20
    22 - 24
    可以确保的是字节码中的行号递增的,而对应的源码中的行号并不是
    */
    public int getLineNumber(int pc) {
        for (int i = lineNumberTable.length - 1; i >= 0; i--) {
            LineNumberTableEntry entry = lineNumberTable[i];
            if (pc >= entry.startPc) {
                return entry.lineNumber;
            }
        }
        return -1;
    }



    static class LineNumberTableEntry {
        int startPc;    //字节码行号
        int lineNumber; //Java源码行号，二者执行的关联

        public LineNumberTableEntry(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
