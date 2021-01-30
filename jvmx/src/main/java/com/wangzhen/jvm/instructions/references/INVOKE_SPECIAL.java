package com.wangzhen.jvm.instructions.references;


import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.instructions.base.MethodInvokeLogic;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.*;

/**
 * desc:调用无须动态绑定的实例方法(包括：构造函数,私有方法,通过super关键字调用的超类方法)
 * JVM特意为这种不需要动态绑定的方法创建的invokespecial,目的是为了加快方法调用(解析)的速度
 *
 * */
public class INVOKE_SPECIAL extends Index16Instruction {

    @Override
    public void execute(ZFrame frame)  {
        // 当前frame 所在的class
        ZClass currentClass = frame.getMethod().getClazz();
       RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
       // 根据INVOKE_SPECIAL 指令后面的操作数拿到对应的方法引用
       MethodRef methodRef = (MethodRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
       ZMethod resolvedMethod = methodRef.resolvedMethod();
       ZClass resolvedClass = methodRef.resolvedClass();
       if("<init>".equals(resolvedMethod.getName())&&resolvedMethod.getClazz()!=resolvedClass){
           throw new NoSuchMethodError(resolvedMethod.getName());
       }
       if(resolvedMethod.isStatic()){
           throw new IncompatibleClassChangeError(resolvedMethod.getName()+"is unstatic contenxt");
       }
       ZObject ref=frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount()-1);

        if (ref == null) {
            throw new NullPointerException("called " + resolvedMethod.getName() + " on a null reference!");
        }

        //验证protected的方法的调用权限
        if (resolvedMethod.isProtected() &&
                resolvedMethod.getClazz().isSuperClassOf(resolvedClass) &&
                !resolvedMethod.getClazz().getPackageName().equals(resolvedClass.getPackageName()) &&
                ref.getClazz() != resolvedClass &&
                !ref.getClazz().isSubClassOf(resolvedClass)) {
            throw new IllegalAccessError();
        }

        ZMethod methodToBeInvoked = resolvedMethod;
        //首先 ACC_SUPER:在jdk1.02之后编译出来的类,该标志均为真;
        //解决 super.func()的形式;但是不能是<init>方法;因为父类中可能定义了func方法,同时子类又覆盖了父类的func,
        //那么解析func的符号引用时首先能在子类中解析到,但此时显示的调用了父类的func方法,所以还需要在父类中去解析;
        if (currentClass.isSuper() &&
                resolvedClass.isSuperClassOf(currentClass) &&
                !"<init>".equals(resolvedMethod.getName())) {
            methodToBeInvoked=MethodLookup.lookupMethodInClass(currentClass.getSuperClass(),methodToBeInvoked.getName(),methodToBeInvoked.getDescriptor());
        }
        if(methodToBeInvoked==null||methodToBeInvoked.isAbstract()){
            throw  new AbstractMethodError(methodToBeInvoked+"is abstract method");
        }
        MethodInvokeLogic.invokeMethod(frame,methodToBeInvoked);


    }
}
