package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classfile.classPackage.ClassReader;

public abstract class ConstantInfo {
    //UTF-8编码的字符串
    public static final int CONSTANT_utf8_info = 1;
    // 整形字面量
    public static final int CONSTANT_Integer_info = 3;
    // 浮点型字面量
    public static final int CONSTANT_Float_info = 4;
    // 长整型字面量
    public static final int CONSTANT_Long_info = 5;
    //双精度浮点型字面量
    public static final int CONSTANT_Double_info = 6;
    //类或接口的符号引用
    public static final int CONSTANT_Class_info = 7;
    // 字符串类型字面量
    public static final int CONSTANT_String_info = 8;
    // 字段的符号引用
    public static final int CONSTANT_Fieldref_info = 9;
    // 类中方法的符号引用
    public static final int CONSTANT_Methodref_info = 10;
    // 接口中方法的符号引用
    public static final int CONSTANT_InterfaceMethodref_info = 11;
    // 字段或方法的符号引用
    public static final int CONSTANT_NameAndType_info = 12;
    // 表示方法句柄
    public static final int CONSTANT_MethodHandle_info = 15;
    // 标志方法类型
    public static final int CONSTANT_MethodType_info = 16;
    // 表示一个动态方法调用点
    public static final int CONSTANT_InvokeDynamic_info = 18;




    // 读取具体的
    abstract void readInfo(ClassReader classReader);

    int type;
    public int getType() {
        return type;
    }







}
