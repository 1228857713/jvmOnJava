package com.wangzhen.jvm.instructions.loads;

import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ZObject;

/**
 *  load变量主要是从局部变量表中 获取一个数据推入到操作数栈中
 */
public class Load {
    public static void aLoad(ZFrame frame, int index){
        ZObject object =frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(object);
    }
    public static void dLoad(ZFrame frame, int index){
        double d =frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(d);

    }
    public static void fLoad(ZFrame frame, int index){
        float f =frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFLoat(f);
    }
    public static void iLoad(ZFrame frame, int index){
        int  i =frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(i);
    }

    public static void lLoad(ZFrame frame, int index){
        long  l =frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(l);
    }

    //用在 load 数组元素时，检测数组是否为 null
    public static void checkNotNull(ZObject arrRef) {
        if (arrRef == null) {
            throw new NullPointerException();
        }
    }

    public static void checkIndex(int count, int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("index: " + index + " array's count: " + count);
        }
    }

}
