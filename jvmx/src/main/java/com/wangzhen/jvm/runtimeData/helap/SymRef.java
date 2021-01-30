package com.wangzhen.jvm.runtimeData.helap;

public class SymRef {
    RuntimeConstantPool runtimeConstantPool;
    String className;
    ZClass zClass;

    public SymRef(RuntimeConstantPool runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    //类引用转直接引用
    public ZClass resolvedClass() {
        if (zClass == null) {
            resolvedClassRef();
        }
        return zClass;
    }

    private void resolvedClassRef() {
        ZClass d = runtimeConstantPool.zClass;
        ZClass c = d.loader.loadClass(className);
        //在这里判断下 d 能否访问 c
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError(d.thisClassName + " can't access " + c.thisClassName);
        }
        zClass = c;
    }
}
