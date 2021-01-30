package com.wangzhen.jvm.runtimeData.helap;

import com.wangzhen.jvm.classfile.classPackage.MemberInfo;

public class ClassMember {
    // 访问标识
    protected int accessFlags;
    protected String name;        //字段、方法名称
    protected String descriptor;  //字段、方法描述
    protected ZClass clazz;       //所属的类，这样可以通过字段或方法访问到它所属的类

    public ClassMember(ZClass zClass, MemberInfo classFileMemberInfo){
        copyMemberInfo(classFileMemberInfo);
        this.clazz = zClass;
    }

    /**
     * 从class文件的memberInfo中复制数据
     *
     * @param memberInfo
     */
    private void copyMemberInfo(MemberInfo memberInfo) {
        accessFlags = memberInfo.getAccessFlags();
        name = memberInfo.getName();
        descriptor = memberInfo.getDescriptor();
    }


    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlag.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlag.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlag.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlag.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlag.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlag.ACC_SYNTHETIC);
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public ZClass getClazz() {
        return clazz;
    }

    public boolean isAccessTo(ZClass d) {
        if (isPublic()) {
            return true;
        }

        if (isProtected()) {
            return d == clazz || d.isSubClassOf(clazz) || d.getPackageName().equals(clazz.getPackageName());
        }

        if (!isPrivate()) {
            return d.getPackageName().equals(clazz.getPackageName());
        }

        return d == clazz;
    }

    public int getAccessFlags() {
        return accessFlags;
    }


    public void setClazz(ZClass clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "ClassMember{" +
                " name='" + name + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
