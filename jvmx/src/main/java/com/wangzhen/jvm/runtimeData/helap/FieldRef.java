package com.wangzhen.jvm.runtimeData.helap;


import com.wangzhen.jvm.classConstant.ConstantFieldRefInfo;

/**
 * Author: zhangxin
 * Time: 2017/7/22
 * Desc: 字段引用
 */
public class FieldRef extends MemberRef {
    ZField field;

    public FieldRef(RuntimeConstantPool runtimeConstantPool, ConstantFieldRefInfo fieldRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(fieldRefInfo);
    }

    //字段引用转直接引用
    public ZField resolvedField() {
        if (field == null) {
            resolvedRefField();
        }

        return field;
    }

    public void resolvedRefField() {
        ZClass d = runtimeConstantPool.zClass;
        // 获取 fieldRef 所在的类
        ZClass c = resolvedClass();
        //在该类中找到对应的字段 field
        ZField field = lookupField(c, name, descriptor);
        if (field == null) {
            throw new NoSuchFieldError("NoSuchFieldError：" + name);
        }

        if (!field.isAccessTo(d)) {
            throw new IllegalAccessError(d.thisClassName + " can't access " + name + "in Class " + c.thisClassName);
        }

        this.field = field;
    }

    private ZField lookupField(ZClass c, String name, String descriptor) {
        for (ZField zf : c.fileds) {
            if (zf.name.equals(name) && zf.getDescriptor().equals(descriptor)) {
                return zf;
            }
        }

        for (ZClass zin : c.interfaces) {
            return lookupField(zin, name, descriptor);
        }

        if (c.superClass != null) {
            return lookupField(c.superClass, name, descriptor);
        }

        return null;
    }

}
