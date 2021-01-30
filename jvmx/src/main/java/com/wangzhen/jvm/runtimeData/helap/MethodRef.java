package com.wangzhen.jvm.runtimeData.helap;


import com.wangzhen.jvm.classConstant.ConstantMethodRefInfo;

/**
 * Author: zhangxin
 * Time: 2017/7/22.
 * Desc: 非接口方法引用;
 */
public class MethodRef extends MemberRef {
    ZMethod method;

    public MethodRef(RuntimeConstantPool runtimeConstantPool, ConstantMethodRefInfo methodRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(methodRefInfo);
    }

    //非接口方法引用转直接引用
    public ZMethod resolvedMethod() {
        if (method == null) {
            resolvedRefMethod();
        }

        return method;
    }

    public void resolvedRefMethod() {
        ZClass d = runtimeConstantPool.zClass;
        //获取 methodRef 所在的类
        ZClass c = resolvedClass();
        if (c.isInterface()) {
            throw new IncompatibleClassChangeError(c.thisClassName);
        }
        //在该类中找到对应的方法
        ZMethod method = lookupMethod(c, name, descriptor);
        if (method == null) {
            throw new NoSuchMethodError("NoSuchMethodError：" + name);
        }

        if (!method.isAccessTo(d)) {
            throw new IllegalAccessError(d.thisClassName + " can't access " + name + "in Class " + c.thisClassName);
        }

        this.method = method;
    }

    //TODO:需验证方法引用，在父类找不到后，是否需要从其接口中再去找？
    private ZMethod lookupMethod(ZClass c, String name, String descriptor) {
        return MethodLookup.lookupMethodInClass(c, name, descriptor);
    }
}
