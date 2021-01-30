package com.wangzhen.jvm.runtimeData.helap;


import com.wangzhen.jvm.classConstant.ConstantInterfaceMethodRefInfo;

/**
 * Author: zhangxin
 * Time: 2017/7/22.
 * Desc:接口方法引用
 */
public class InterfaceMethodRef extends MemberRef {
    ZMethod method;

    public InterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, ConstantInterfaceMethodRefInfo interfaceMethodRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(interfaceMethodRefInfo);
    }

    //接口方法引用转直接引用
    public ZMethod resolvedInterfaceMethod() {
        if (method == null) {
            resolveInterfaceMethodRef();
        }
        return method;
    }

    private void resolveInterfaceMethodRef() {
        ZClass d = runtimeConstantPool.zClass;
        //获取 methodRef 所在的接口
        ZClass c = resolvedClass();
        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError(c.thisClassName);
        }
        //在该类中找到对应的方法
        ZMethod method = lookupInterfaceMethod(c, name, descriptor);
        if (method == null) {
            throw new NoSuchMethodError("NoSuchMethodError：" + name);
        }

        if (!method.isAccessTo(d)) {
            throw new IllegalAccessError(d.thisClassName + " can't access " + name + "in Class " + c.thisClassName);
        }

        this.method = method;
    }

    private ZMethod lookupInterfaceMethod(ZClass iface, String name, String descriptor) {
        for (ZMethod method : iface.methods) {
            if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                return method;
            }
        }
        return MethodLookup.lookupMethodInInterfaces(iface.interfaces, name, descriptor);
    }
}
