package com.wangzhen.jvm.instructions.stores;

import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ZObject;

/**
 * Store 变量主要从 操作数张中 弹出一个数 推入到局部变量表中
 */
public class Store {
    public static void astore(ZFrame frame, int index) {
        ZObject ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, ref);
    }

    public static void istore(ZFrame frame, int index) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }

    public static void lstore(ZFrame frame, int index) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }

    public static void fstore(ZFrame frame, int index) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }

    public static void dstote(ZFrame frame, int index) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }

    //用在 store 数组元素时，检测数组是否为 null
    public static void checkNotNull(ZObject arrRef) {
        if (arrRef == null) {
            throw new NullPointerException();
        }
    }

    /**
     *
     * @param count  数组的长度
     * @param index 素组的索引值
     */
    public static void checkIndex(int count, int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("index: " + index + " array's count: " + count);
        }
    }
}
