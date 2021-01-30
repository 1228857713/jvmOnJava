package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ClassRef;
import com.wangzhen.jvm.runtimeData.helap.RuntimeConstantPool;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZObject;


/**
 * @author zachaxy
 * @date 17/12/29
 * desc:创建引用类型数组,注意这里是一维的!!!
 * 如果创建的是 String[],那么从运行时常量池拿到 String 的符号引用，通过符号引用将 String 类加载进来
 * 接下来再将 String[] 类加载进来！
 * 最后通过 String[] 类创建 字符串数组对象，并压入操作数栈
 */
public class ANEW_ARRAY extends Index16Instruction {
    @Override
    public void execute(ZFrame frame)  {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getRuntimeConstant(index).getValue();

        ZClass componetClass = classRef.resolvedClass();
        OperandStack operandStack=frame.getOperandStack();
        int count = operandStack.popInt();
        if(count<0){
            throw new NegativeArraySizeException("" + count);
        }
        // 根据基本类型得到数组类型，如根据 java/lang/string 得到 [Ljava/lang/string
        ZClass arrayClass = componetClass.arrayClass();
        // 根据数组类得到数组对象
        ZObject arrayObject = arrayClass.newArray(count);
        operandStack.pushRef(arrayObject);

    }
}
