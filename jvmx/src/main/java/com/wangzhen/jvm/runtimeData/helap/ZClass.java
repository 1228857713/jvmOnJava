package com.wangzhen.jvm.runtimeData.helap;

import com.wangzhen.jvm.classfile.classPackage.ClassFile;
import com.wangzhen.jvm.runtimeData.Slots;

public class ZClass {
    // 访问标识符
    private int accessFlags;
    // 当前类的名字（完全限定名）
    public String thisClassName;
    //父类的名字 （完全限定名）
    public String superClassName;
    //接口名字(完全限定名,不可以为null,若为接口,数组大小为0)
    public String[] interfaceNames;
    //运行时常量池,注意和class文件中常量池区别;
    private RuntimeConstantPool runtimeConstantPool;
    //字段表,包括静态和非静态，此时并不分配 slotId；下面的staticVars 是其子集
    ZField[] fileds;
    //方法表，包括静态和非静态
    ZMethod[] methods;
    //类加载器
    ZClassLoader loader;
    //当前类的父类class,由类加载时,给父类赋值;
    ZClass superClass;
    //当前类的接口class,由类加载时,给父类赋值;
    ZClass[] interfaces;
    //非静态变量占用slot大小,这里只是统计个数(从顶级父类Object开始算起)
    int instanceSlotCount;
    // 静态变量所占空间大小
    int staticSlotCount;
    // 存放静态变量
    Slots staticVars;
    //判断类是否已经初始化，执行了类的<clinit>方法
    boolean initStarted;
    // jObject 指向的是该copyAttributes类的元类对象obj。 eg：String.class 得到的结果
    ZObject jObject;
    String sourceFile;

    public ZClass(ClassFile classFile) {
        this.accessFlags = classFile.getAccessFlags();
        this.thisClassName=classFile.getThisClassName();
        this.superClassName=classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();
        runtimeConstantPool = new RuntimeConstantPool(this,classFile.getConstantPool());
        fileds = ZField.makeFields(this,classFile.getFields());
        methods = ZMethod.makeMethods(this,classFile.getMethods());
        sourceFile = classFile.getSourceFile();

    }

    //用来创建数组类型
    public ZClass(int accessFlags, String thisClassName, ZClassLoader loader,
                  boolean initStarted, ZClass superClass, ZClass[] interfaces) {
        this.accessFlags = accessFlags;
        this.thisClassName = thisClassName;
        this.loader = loader;
        this.initStarted = initStarted;
        this.superClass = superClass;
        this.interfaces = interfaces;
    }



    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlag.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlag.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (accessFlags & AccessFlag.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (accessFlags & AccessFlag.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlag.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlag.ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
        return 0 != (accessFlags & AccessFlag.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (accessFlags & AccessFlag.ACC_ENUM);
    }

    public boolean isAccessibleTo(ZClass other) {
        return isPublic() || getPackageName().equals(other.getPackageName());
    }

    public String getPackageName() {
        int i = thisClassName.lastIndexOf("/");
        if (i > 0) {
            return thisClassName.substring(0, i);
        }
        return "";
    }


    public int getAccessFlags() {
        return accessFlags;
    }

    public String getThisClassName() {
        return thisClassName;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public RuntimeConstantPool getRuntimeConstantPool() {
        return runtimeConstantPool;
    }

    public ZField[] getFileds() {
        return fileds;
    }

    public ZMethod[] getMethods() {
        return methods;
    }

    public ZClassLoader getLoader() {
        return loader;
    }

    public ZClass getSuperClass() {
        return superClass;
    }

    public ZClass[] getInterfaces() {
        return interfaces;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public Slots getStaticVars() {
        return staticVars;
    }

    public boolean isInitStarted() {
        return initStarted;
    }

    public ZObject getjObject() {
        return jObject;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public boolean isSubClassOf(ZClass parent) {
        for (ZClass c = superClass; c != null; c = c.superClass) {
            if (c == parent) {
                return true;
            }
        }
        return false;
    }

    public void startInit(){
        initStarted = true;
    }

    public boolean isSuperClassOf(ZClass sub) {
        return sub.isSubClassOf(this);
    }

    //这里不太好理解，该方法是在下面的 isImplements 方法中被调用的，调用方是类的接口
    //因此下面的 interfaces 数组表明的不是 source 的接口，而是 source 的某一个接口的接口
    //虽然接口 sub 在java 语法中是用 extends 继承父接口 parent，但是其字节码中，parent 是 sub 的接口而不是父类
    public boolean isSubInterfaceOf(ZClass iface) {
        for (ZClass superInterface : interfaces) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) {
                return true;
            }
        }
        return false;
    }
    private boolean isSuperInterfaceOf(ZClass source) {
        return source.isSubInterfaceOf(this);
    }

    public boolean isImplements(ZClass iface) {
        for (ZClass c = this; c != null; c = c.superClass) {
            for (int i = 0; i < c.interfaces.length; i++) {
                if (c.interfaces[i] == iface || c.interfaces[i].isSubInterfaceOf(iface)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAssignableFrom(ZClass source) {
        // source 是否由 target 扩展而来（子类）
        ZClass target = this;
        if (source == target) {
            return true;
        }

        if (!source.isArray()) {
            if (!source.isInterface()) {
                if (!target.isInterface()) {
                    return source.isSubClassOf(target);
                } else {
                    // target 是接口
                    return source.isImplements(target);
                }
            } else {
                // source 是接口
                if (!target.isInterface()) {
                    return target.isJlObject();
                } else {
                    // target 也是接口
                    return target.isSuperInterfaceOf(source);
                }
            }
        } else {
            //source 是数组
            if (!target.isArray()) {
                if (!target.isInterface()) {
                    return target.isJlObject();
                } else {
                    // target 是接口
                    // t is interface;数组默认实现了Cloneable和Serializable接口
                    return target.isJlCloneable() || target.isJioSerializable();
                }
            } else {
                // target 也是数组
                ZClass sc = source.getComponentClass();
                ZClass tc = target.getComponentClass();
                return sc == tc || tc.isAssignableFrom(source);
            }
        }
    }

    public boolean isJlObject() {
        return "java/lang/Object".equals(thisClassName);
    }

    public boolean isJlCloneable() {
        return "java/lang/Cloneable".equals(thisClassName);
    }

    public boolean isJioSerializable() {
        return "java/io/Serializable".equals(thisClassName);
    }

    // 为对象分配存储空间
    public ZObject newObject() {
        return new ZObject(this);
    }

    public ZClass arrayClass() {
        String arrayClassName = ClassNameHelper.getArrayClassName(thisClassName);
        return loader.loadClass(arrayClassName);
    }

    //根据方法名和描述符获取方法，在测试环境中使用；
    public ZMethod getMethod(String name, String desc) {
        for (ZClass clazz = this; clazz != null; clazz = clazz.superClass) {
            for (ZMethod method : methods) {
                if (method.name.equals(name) && method.descriptor.equals(desc)) {
                    return method;
                }
            }
        }
        return null;
    }

    public ZField getField(String name, String descriptor, boolean isStatic) {
        for (ZClass clazz = this; clazz != null; clazz = clazz.superClass) {
            for (ZField field : clazz.fileds) {
                if (field.isStatic() == isStatic &&
                        field.name.equals(name) &&
                        field.descriptor.equals(descriptor)) {
                    return field;
                }
            }
        }
        return null;
    }
    //---------------针对数组相关的方法
    public boolean isArray() {
        return thisClassName.startsWith("[");
    }

    public ZObject newArray(int count) {
        if (!isArray()) {
            throw new RuntimeException("Not array class: " + thisClassName);
        }
        switch (thisClassName) {
            case "[Z":
                return new ZObject(this, new boolean[count], null);
            case "[B":
                return new ZObject(this, new byte[count], null);
            case "[C":
                return new ZObject(this, new char[count], null);
            case "[S":
                return new ZObject(this, new short[count], null);
            case "[I":
                return new ZObject(this, new int[count], null);
            case "[J":
                return new ZObject(this, new long[count], null);
            case "[F":
                return new ZObject(this, new float[count], null);
            case "[D":
                return new ZObject(this, new double[count], null);
            default:
                return new ZObject(this, new ZObject[count], null);
        }
    }

    public ZClass getComponentClass() {
        String componentClassName = ClassNameHelper.getComponentClassName(thisClassName);
        return loader.loadClass(componentClassName);
    }

    // 得到类中的main 方法
    public ZMethod getMainMethod() {
        return this.getStaticMethod("main", "([Ljava/lang/String;)V", true);
    }

    // 查找类中的方法
    private ZMethod getStaticMethod(String name, String descriptor, boolean isStatic) {
        for (ZClass c = this; c != null; c = c.superClass) {
            if (null == c.methods) {
                continue;
            }
            for (ZMethod method : c.methods) {
                if (method.isStatic() == isStatic && method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
        }
        throw new RuntimeException("method not find: " + name + " " + descriptor);
    }

    @Override
    public String toString() {
        return thisClassName;
    }
}
