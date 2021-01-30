package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.ClassInitLogic;
import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.instructions.base.MethodInvokeLogic;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.*;

/**
 * @author zachaxy
 * @date 17/12/27
 * desc:静态方法调用指令
 * 静态方法,在调用阶段即可确定是哪个方法
 */
public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(ZFrame frame)  {
        ZMethod method = frame.getMethod();
        ZClass zClass = method.getClazz();
        RuntimeConstantPool runtimeConstantPool = zClass.getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
        ZMethod resolvedMethod = methodRef.resolvedMethod();
        if (!resolvedMethod.isStatic()){
            throw new IncompatibleClassChangeError("static method cannot invoke on instance");
        }
        ZClass resolvedMethodClazz = resolvedMethod.getClazz();
        if(!resolvedMethodClazz.isInitStarted()){
            frame.revertPc();
            ClassInitLogic.initClass(resolvedMethodClazz,frame.getThread());
        }
        MethodInvokeLogic.invokeMethod(frame,resolvedMethod);

    }
}
