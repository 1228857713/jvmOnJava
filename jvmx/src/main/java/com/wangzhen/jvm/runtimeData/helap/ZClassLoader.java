package com.wangzhen.jvm.runtimeData.helap;

import com.wangzhen.jvm.classfile.classPackage.ClassFile;
import com.wangzhen.jvm.classfile.classPath.ClassPath;
import com.wangzhen.jvm.runtimeData.Slots;

import java.util.HashMap;
import java.util.Map;

/**
 * 模仿java的 classLoader
 */
public class ZClassLoader {
      ClassPath classPath;
      HashMap<String,ZClass> map;


    public ZClassLoader(ClassPath classPath) {
        this.classPath = classPath;
        this.map = new HashMap<String, ZClass>();
        loadBasicClasses();
        loadPrimitiveClasses();
    }

    //先查找classMap，看类是否已经被加载。如果是，直接返回类数据，否则调用loadNonArrayClass（）方法加载类。
    //在类方法中的一个递归调用,也是classLoader中的入口方法
    public ZClass loadClass(String name) {
        if(map.containsKey(name)){
            return map.get(name);
        }
        ZClass zClass;
        // 如果是数组
        if(name.charAt(0)=='['){
            zClass = loadArrayClass(name);
        }else{
            // 加载普通类
            zClass = loadNonArrayClass(name);
        }
        // 为每一个class 都关联一个元类
        ZClass jlcClass = map.get("java/lang/Class");
        if(jlcClass!=null){
            zClass.jObject = jlcClass.newObject();
            zClass.jObject.extra = zClass;
        }
        return zClass;
    }

    public ZClass loadArrayClass(String className){
        ZClass zClass = new ZClass(AccessFlag.ACC_PUBLIC,className,this,true, loadClass("java/lang/Object"),new ZClass[]{loadClass("java/lang/Cloneable"), loadClass("java/io/Serializable")});
        map.put(className,zClass);
        return zClass;
    }

    /**
     * @desc  根据类的名字（全路径）将该类记载到内存中，并将其转换为类对象(ZClass)
     * @param name 类名
     * @return  返回一个class 的对象
     */
    private ZClass loadNonArrayClass(String name){
       // 加载class 文件到方法区内(这里是自己定义的map)
        ZClass clazz = loadClassFile(name);
        // 链接
        link(clazz);
        return clazz;
    }


    /**
     * @desc: 1.通过类的全限定名称获取该类的二进制流，将该类加载到内存中
     *        2.将内存中的静态的存储结构转换为方法区的运行时的数据结构
     *        3.在堆中生成一个代表该类的Class对象。作为方法区这些数据结构的访问入口。
     * @param name
     * @return
     */
    public ZClass loadClassFile(String name){
        byte[]data = readClass(name);
        ZClass zClass = this.parseClass(data);
        zClass.loader =  this;
        resolveSuperClass(zClass);
        resolveInterfaces(zClass);
        map.put(zClass.thisClassName,zClass);
        return zClass;
    }

    /**
     * @desc  类链接的三个阶段，验证->准备->解析
     *        1.验证：验证 class文件是否合法，比如 前四个字节是否是 0xcafebabe，class的版本是否被当前运行的jdk所支持
     *        2.准备
     *        3.解析
     * @param zClass
     */
    public void link(ZClass zClass){
        // 验证
        verify(zClass);
        // 准备
        prepare(zClass);
        // 解析
    }

    //在执行类的任何代码之前要对类进行严格的检验,这里忽略检验过程,作为空实现;
    private void verify(ZClass clazz) {
        //文件格式验证
        //元数据验证
        //字节码验证
        //符号引用验证
    }

    // 给类变量分配空间并赋予初始值
    // 实例变量会在对象被初始化的时候赋值。
    private void prepare(ZClass clazz) {
        // 计算静态变量需要的slot的值
        calcStaticFieldSlotIds(clazz);
        // 计算实例变量需要的slot值
        calcInstanceFieldSlotIds(clazz);
        // 分配并且初始化静态变量的值，这里只是给零值 如 int=>0  object=>null
        // 如果是 static final 类型的变量会直接赋予值
        allocAndInitStaticVars(clazz);
    }

    // 计算new一个对象所需的空间,单位是clazz.instanceSlotCount,主要包含了类的非静态成员变量(包含父类的)
    // 但是这里并没有真正的申请空间，只是计算大小，同时为每个非静态变量关联 slotId
    private void calcInstanceFieldSlotIds(ZClass clazz) {
        int slotId = 0;
        if (clazz.superClass != null) {
            slotId = clazz.superClass.instanceSlotCount;
        }

        for (ZField field : clazz.fileds) {
            if (!field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                // long 型和 double 型需要占用2个slot
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.instanceSlotCount = slotId;
    }

    //计算类的静态成员变量所需的空间，不包含父类，同样也只是计算和分配slotId，不申请空间
    private void calcStaticFieldSlotIds(ZClass clazz) {
        int slotId = 0;
        for (ZField field : clazz.fileds) {
            if (field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    // long 和 double 需要占用2个 slot
                    slotId++;
                }
            }
        }
        clazz.staticSlotCount = slotId;
    }

    // 为静态变量申请空间,注意:这个申请空间的过程,就是将所有的静态变量赋零值 即为0或者null;
    // 如果是static final 的基本类型或者String，其值会保存在ConstantValueAttribute属性中
    private void allocAndInitStaticVars(ZClass clazz) {
        // new 出对象对java程序来说会默认赋值
        clazz.staticVars = new Slots(clazz.staticSlotCount);
        for (ZField field : clazz.fileds) {
            // 如果是 static final 类型 那么就直接赋值
            if (field.isStatic() && field.isFinal()) {
                  // 初始化 static final 类型的值
                  initStaticFinalVar(clazz, field);
            }
        }
    }

    // 将class文件解析成 运行时class
    private ZClass parseClass(byte[] data) {
        ClassFile cf = new ClassFile(data);
        ZClass clz = new ZClass(cf);
        return clz;
    }

    //加载当前类的父类,除非是Object类，否则需要递归调用LoadClass()方法加载它的超类
    //默认情况下,父类和子类的类加载器是同一个;
    private void resolveSuperClass(ZClass clazz) {
        if (!"java/lang/Object".equals(clazz.thisClassName)) {
            clazz.superClass = clazz.loader.loadClass(clazz.superClassName);
        }
    }

    //加载当前类的接口类
    private void resolveInterfaces(ZClass clazz) {
        int count = clazz.interfaceNames.length;
        clazz.interfaces = new ZClass[count];
        for (int i = 0; i < count; i++) {
            clazz.interfaces[i] = clazz.loader.loadClass(clazz.interfaceNames[i]);
        }
    }

    private void loadBasicClasses() {
        //经过这一步load之后,classMap中就有Class的Class了，以及Object 和 Class 所实现的接口；
        ZClass jlClassClass = loadClass("java/lang/Class");
        //接下来对classMap中的每一个Class都创建一个jClass;使用jlClassClass.NewObject()方法;
        // 通过调用 newObject 方法，为每一个 Class 都创建一个元类对象；这样在使用 String.class 时可以直接获取到；
        for (Map.Entry<String, ZClass> entry : map.entrySet()) {
            ZClass jClass = entry.getValue();
            if (jClass.jObject == null) {
                jClass.jObject = jlClassClass.newObject();
                jClass.jObject.extra = jClass;
            }
        }
    }

    //加载基本类型的类:void.class;boolean.class;byte.class
    private void loadPrimitiveClasses() {
        for (Map.Entry<String, String> entry : ClassNameHelper.primitiveTypes.entrySet()) {
            String className = entry.getKey();
            loadPrimitiveClass(className);
        }
    }

    //加载基本类型,和数组类似,也没有对应的class文件,只能在运行时创建;基本类型:无超类,也没有实现任何接口
    /* 针对基本类型的三点说明：
    1. void和基本类型的类型名字就是：void，int，float 等
    2. 基本类型的类没有超类，也没有实现任何接口
    3. 非基本类型的类对象是通过 ldc 指令加载到操作数栈中的
    */
    private void loadPrimitiveClass(String className) {
        ZClass clazz = new ZClass(AccessFlag.ACC_PUBLIC, className, this, true,
                null,
                new ZClass[]{});
        clazz.jObject = map.get("java/lang/Class").newObject();
        clazz.jObject.extra = clazz;
        map.put(className, clazz);
    }



            /**
             * 利用 ClassPath 把 class 文件读进来
             *
             * @param name 类名，eg：java.lang.String 或者包含 main 方法的主类名
             * @return class 字节数据
             */
    private byte[] readClass(String name) {
        byte[] data = classPath.readClass(name);
        if (data != null) {
            return data;
        } else {
            throw new ClassCastException("class name: " + name);
        }
    }

    // 为static final 修饰的成员赋值,这种类型的成员是ConstantXXXInfo类型的,该info中包含真正的值;
    private void initStaticFinalVar(ZClass clazz, ZField zfield) {
        Slots staticVars = clazz.staticVars;
        RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
        int index = zfield.constValueIndex;
        int slotId = zfield.slotId;
        if (index > 0) {
            switch (zfield.getDescriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    int intValue = (int) runtimeConstantPool.getRuntimeConstant(index).getValue();
                    staticVars.setInt(slotId, intValue);
                    break;
                case "J":
                    long longValue = (long) runtimeConstantPool.getRuntimeConstant(index).getValue();
                    staticVars.setLong(slotId, longValue);
                    break;
                case "F":
                    float floatValue = (float) runtimeConstantPool.getRuntimeConstant(index).getValue();
                    staticVars.setFloat(slotId, floatValue);
                    break;
                case "D":
                    double doubleValue = (double) runtimeConstantPool.getRuntimeConstant(index).getValue();
                    staticVars.setDouble(slotId, doubleValue);
                    break;
                case "Ljava/lang/String;":
                    String stringValue = (String) runtimeConstantPool.getRuntimeConstant(index).getValue();
                    ZObject jStr = StringPool.jString(clazz.getLoader(), stringValue);
                    staticVars.setRef(slotId,jStr);
                default:
                    break;
            }
        }

    }

}
