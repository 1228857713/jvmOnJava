package com.wangzhen.jvm.runtimeData.helap;


import com.wangzhen.jvm.attribute.CodeAttribute;

/**
 * @author zachaxy
 * @date 18/1/2
 */
public class ExceptionTable {
    private ExceptionHandler[] handlers;

    public ExceptionTable(CodeAttribute.ExceptionTableEntry[] entry, RuntimeConstantPool runtimeConstantPool) {
        handlers = new ExceptionHandler[entry.length];
        for (int i = 0; i < entry.length; i++) {
            handlers[i] = new ExceptionHandler();
            handlers[i].startPc = entry[i].getStartPc();
            handlers[i].endPc = entry[i].getEndPc();
            handlers[i].handlerPc = entry[i].getHandlerPc();
            handlers[i].catchType = getCatchType(entry[i].getCatchType(), runtimeConstantPool);
        }
    }

    //将classFile中的异常类型(符号引用)转换为运行时的直接引用
    public ClassRef getCatchType(int index, RuntimeConstantPool runtimeConstantPool) {
        if (index == 0) {
            // catch all
            return null;
        }
        return (ClassRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
    }


    /*
     返回能解决当前Exception的handler=>多个catch块,决定用哪个
     判断方法：
        1.当前抛出异常的代码块是否在异常的捕获范围内（即通过pc 的行号来处理）
        2.看exceptionhandle 的异常是否是抛出的异常，或者是其父类
     如果满足了上述条件那么就返回 handler。
     */
    public ExceptionHandler findExceptionHandler(ZClass exClazz, int pc) {
        for (int i = 0; i < handlers.length; i++) {
            ExceptionHandler handler = handlers[i];
            if (pc >= handler.startPc && pc < handler.endPc) {
                // 为空表示 catch all
                if (handler.catchType == null) {
                    return handler;
                }
                // 如果catch 的异常是实际抛出的异常的父类，也可以捕获
                ZClass catchClazz = handler.catchType.resolvedClass();
                if (catchClazz == exClazz || catchClazz.isSuperClassOf(exClazz)) {
                    return handler;
                }
            }
        }
        return null;
    }
}

class ExceptionHandler {
    int startPc;
    int endPc;
    int handlerPc;
    ClassRef catchType;
}