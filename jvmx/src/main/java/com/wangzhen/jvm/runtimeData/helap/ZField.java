package com.wangzhen.jvm.runtimeData.helap;

import com.wangzhen.jvm.attribute.ConstantValueAttribute;
import com.wangzhen.jvm.classfile.classPackage.MemberInfo;

public class ZField extends ClassMember{

    /**
     * 1.运行时常量池中的索引,该属性只有在static final成员有初值的情况下才有;说明被 static final 修饰的变量
     *   在类的准备阶段就会为其赋值 {@link com.wangzhen.jvm.runtimeData.helap.ZClassLoader#allocAndInitStaticVars }
     * 2.根据测试，只有在 static final 修饰的是基本变量或者String的时候的时候constValueIndex在才有值，而如果是引用对象的时候
     *   constValueIndex 是空，其变量的赋值操作会推迟到类的初始化操作中(<clinit>)，和普通的静态变量是一样的。所以 initStaticFinalVar 方法
     *   {@link com.wangzhen.jvm.runtimeData.helap.ZClassLoader#initStaticFinalVar }
     *   的实现并没对象和数组也就是 'L' '[' 的。
     */
    int constValueIndex;
    //类中字段数组slots中的的索引；其赋值在首次加载 class 文件后，为其分配的 slotId
    //如果是静态字段，该 slotId 表示的是在 Class 中staticVars数组中的索引
    //如果是非静态字段，该 slotId 表示的是在 Object 中 fields 数组中的索引
    int slotId;

    public ZField(ZClass zClass, MemberInfo classFileMemberInfo) {

        super(zClass, classFileMemberInfo);
        copyAttributes(classFileMemberInfo);
    }


    public void copyAttributes(MemberInfo classFileField) {
        ConstantValueAttribute constantValueAttribute = classFileField.getConstantValueAttribute();
        if (constantValueAttribute != null) {
            constValueIndex = constantValueAttribute.getConstantvalueIndex();
        }
    }

    public static ZField[] makeFields(ZClass zclass, MemberInfo[] cfFields) {
        ZField[] fields = new ZField[cfFields.length];
        for (int i = 0; i < fields.length; i++) {
            ZField field = new ZField(zclass, cfFields[i]);
            fields[i] = field;
        }
        return fields;
    }

    public boolean isVolatile() {
        return 0 != (accessFlags & AccessFlag.ACC_VOLATILE);
    }

    public boolean isTransient() {
        return 0 != (accessFlags & AccessFlag.ACC_TRANSIENT);
    }

    public boolean isEnum() {
        return 0 != (accessFlags & AccessFlag.ACC_ENUM);
    }


    public int getConstValueIndex() {
        return constValueIndex;
    }

    public int getSlotId() {
        return slotId;
    }

    public boolean isLongOrDouble() {
        return getDescriptor().equals("J") || getDescriptor().equals("D");
    }
}
