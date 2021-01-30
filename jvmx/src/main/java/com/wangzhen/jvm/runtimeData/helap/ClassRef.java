package com.wangzhen.jvm.runtimeData.helap;


import com.wangzhen.jvm.classConstant.ConstantClassInfo;

/**
 * Author: zhangxin
 * Time: 2017/7/22.
 * Desc: 类引用
 */
public class ClassRef extends SymRef {
    public ClassRef(RuntimeConstantPool runtimeConstantPool, String className) {
        super(runtimeConstantPool);
        this.className = className;
    }
}
