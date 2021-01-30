package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.instructions.base.MethodInvokeLogic;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.*;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(ZFrame frame)  {
        // 调用该方法的所在类
        ZClass currentClass = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
        //将方法引用转换为方法
        //这一步拿到解析后的resolvedMethod主要是用来做下面权限的验证;
        //而真正的resolvedMethod是在下面拿到真正的调用者,再次解析到的methodToBeInvoked
        ZMethod resolvedMethod = methodRef.resolvedMethod();

        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError(resolvedMethod.getName() + " in unstatic context");
        }
        //从操作数栈中获取调用该非静态方法的引用;参数的传递是从当前frame的操作数栈中根据参数个数,完整的拷贝到调用frame的本地变量表中;
        ZObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);

        if (ref == null) {
            throw new NullPointerException("called " + resolvedMethod.getName() + " on a null reference!");
        }

        //验证protected的方法的调用权限
        if (resolvedMethod.isProtected() &&
                resolvedMethod.getClazz().isSuperClassOf(currentClass) &&
                !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                ref.getClazz() != currentClass &&
                !ref.getClazz().isSubClassOf(currentClass)) {

            if (!(ref.getClazz().isArray() && "clone".equals(resolvedMethod.getName()))) {
                throw new IllegalAccessError(resolvedMethod.getName() + "called in " + ref.getClazz().thisClassName);
            }
        }

        ZMethod methodTobeInvoke = MethodLookup.lookupMethodInClass(ref.getClazz(),resolvedMethod.getName(),resolvedMethod.getDescriptor());
        MethodInvokeLogic.invokeMethod(frame,methodTobeInvoke);
    }
}
