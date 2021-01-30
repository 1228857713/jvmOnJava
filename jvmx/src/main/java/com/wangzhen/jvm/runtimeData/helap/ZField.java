package com.wangzhen.jvm.runtimeData.helap;

import com.wangzhen.jvm.attribute.ConstantValueAttribute;
import com.wangzhen.jvm.classfile.classPackage.MemberInfo;

public class ZField extends ClassMember{

    //运行时常量池中的索引,该属性只有在static final成员有初值的情况下才有;
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
