package com.wangzhen.jvm.classConstant;


import com.wangzhen.jvm.classfile.classPackage.ClassReader;

/**
 * Author: zhangxin
 * Time: 2017/5/2 0002.
 * Desc:
 * CONSTANT_Fieldref_info表示字段符号引用
 * CONSTANT_Methodref_info表示普通（非接口）方法符号引用
 * CONSTANT_InterfaceMethodref_info表示接口方法符号引用
 * 这三种类型结构一样,所以给出统一的类结构;
 * 然后定义三个类继承这个超类;
 * class_index和name_and_type_index都是常量池索引，分别指向CONSTANT_Class_info和CONSTANT_NameAndType_info常量。
 */
/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
 */
public class ConstantMemberRefInfo extends ConstantInfo {
    ConstantPool constantPool;
    int classIndex;
    int nameAndTypeIndex;

    //    该构造方法是供子类调用的,虽然有三个子类,但是并没有使用过该子类,因为当前类(父类)已经满足需求了;
//    public ConstantMemberRefInfo(ConstantPool constantPool) {
//        this.constantPool = constantPool;
//    }

    //    该构造方法是供外部调用的;
    public ConstantMemberRefInfo(ConstantPool constantPool, int type,ClassReader reader) {
        this.constantPool = constantPool;
        this.type = type; //因为接口,方法,字段通用这一个类,所以在构造方法中传入 i 来区分不同的类型;
        readInfo(reader);
    }


    @Override
    void readInfo(ClassReader reader) {
        classIndex = reader.readNByteToInt(2);
        nameAndTypeIndex = reader.readNByteToInt(2);
    }

    public String getClassName() {
        return constantPool.getClassName(classIndex);
    }

    public String[] getNameAndDescriptor() {
        return constantPool.getNameAndType(nameAndTypeIndex);
    }


    //下面两个方法是将上面的单独分开拿出来的,
    public String getName() {
        return constantPool.getName(nameAndTypeIndex);
    }

    public String getDescriptor() {
        return constantPool.getType(nameAndTypeIndex);
    }



}
