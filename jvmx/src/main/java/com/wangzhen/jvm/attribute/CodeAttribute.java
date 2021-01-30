package com.wangzhen.jvm.attribute;


import com.wangzhen.jvm.classConstant.ConstantPool;
import com.wangzhen.jvm.classfile.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class CodeAttribute extends AttributeInfo{
    ConstantPool constantPool;
    int maxStack;
    int maxLocals;
    int codeLength;
    byte[] code;
    int exceptionTableLength;
    ExceptionTableEntry[] exceptionTables;
    AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader classReader) {
        maxStack = ByteUtils.bytesToInt(classReader.readNByte(2));
        maxLocals = ByteUtils.bytesToInt(classReader.readNByte(2));
        codeLength = ByteUtils.bytesToInt(classReader.readNByte(4));
        code = classReader.readNByte(codeLength);
        exceptionTableLength = ByteUtils.bytesToInt(classReader.readNByte(2));
        readExceptionTables(classReader,exceptionTableLength);
        attributes = readAttributeInfos(classReader,constantPool);

    }



    public void readExceptionTables(ClassReader classReader,int exceptionTableLength ) {
        exceptionTables = new ExceptionTableEntry[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            ExceptionTableEntry exceptionTable = new ExceptionTableEntry(classReader);
            exceptionTables[i] = exceptionTable;
        }
    }

    public LineNumberTableAttribute lineNumberTableAttribute() {
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i] instanceof LineNumberTableAttribute) {
                return (LineNumberTableAttribute) attributes[i];
            }
        }
        return null;
    }

    //异常表，包含四个指针，分别为
    public static class ExceptionTableEntry {
        int startPc;        //可能排除异常的代码块的起始字节码（包括）
        int endPc;          //可能排除异常的代码块的终止字节码（不包括）
        int handlerPc;      //负责处理异常的 catch 块的其实位置
        int catchType;      //指向运行时常量池的一个索引，解析后可以得到一个异常类


        //改为传入一个reader的方法,比上面的构造方法更优雅一些;
        public ExceptionTableEntry(ClassReader reader) {
            this.startPc = reader.readNByteToInt(2);
            this.endPc = reader.readNByteToInt(2);
            this.handlerPc = reader.readNByteToInt(2);
            this.catchType = reader.readNByteToInt(2);
        }

        public int getStartPc() {
            return startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public byte[] getCode() {
        return code;
    }

    public int getExceptionTableLength() {
        return exceptionTableLength;
    }

    public ExceptionTableEntry[] getExceptionTables() {
        return exceptionTables;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }
}
