package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.Index8Instruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.RuntimeConstantPool;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZClassLoader;
import com.wangzhen.jvm.runtimeData.helap.ZObject;

/**
 * 创建基本类型的数组指令，一维的
 */
public class NEW_ARRAY extends Index8Instruction {

    //Array Type  atype
    private final int AT_BOOLEAN = 4;
    private final int AT_CHAR = 5;
    private final int AT_FLOAT = 6;
    private final int AT_DOUBLE = 7;
    private final int AT_BYTE = 8;
    private final int AT_SHORT = 9;
    private final int AT_INT = 10;
    private final int AT_LONG = 11;

    @Override
    public void execute(ZFrame frame)  {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        //int type = runtimeConstantPool.getRuntimeConstant(index).getType();
        OperandStack operandStack = frame.getOperandStack();
        // 得到数组的大小
        int count = operandStack.popInt();
        if(count<1){
            throw new NegativeArraySizeException(count+"");
        }
        ZClass arrayClass = getPrimitiveArrayClass(frame.getMethod().getClazz().getLoader());
        ZObject arrObj = arrayClass.newArray(count);
        operandStack.pushRef(arrObj);



    }

    public ZClass getPrimitiveArrayClass(ZClassLoader classLoader){
        // 这里的指令码 不再是指向运行时常量池了，而是代表基本数据类型
        switch (index){
            case AT_BOOLEAN:
                return classLoader.loadClass("[Z");
            case AT_BYTE:
                return classLoader.loadClass("[B");
            case AT_CHAR:
                return classLoader.loadClass("[C");
            case AT_SHORT:
                return classLoader.loadClass("[S");
            case AT_INT:
                return classLoader.loadClass("[I");
            case AT_LONG:
                return classLoader.loadClass("[L");
            case AT_FLOAT:
                return classLoader.loadClass("[F");
            case AT_DOUBLE:
                classLoader.loadClass("[D");
            default:
                throw new RuntimeException("Invaild atye");

        }

    }
}
