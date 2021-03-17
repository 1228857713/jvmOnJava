package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.OperandStack;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;
import com.wangzhen.jvm.runtimeData.helap.ZObject;
import com.wangzhen.jvm.znative.java.lang.NStackTraceElement;

/**
 * Description: 异常处理指令
 * Datetime:    2020/9/28   8:48 下午
 * Author:   王震
 */
public class ATHROW extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        // 抛出异常都会 新建一个异常 推入到操作数栈顶
        ZObject ex = frame.getOperandStack().popRef();
        if(ex == null){
            throw new NullPointerException("没有这个对象");
        }
        ZThread thread = frame.getThread();
        // 如果在调用链中没有找到处理异常的方法，那么就把该异常给jvm 处理
        if(!findAndGotoExceptionHandler(thread,ex)){
            handleUncaughtException(thread,ex);
        }
    }

    public boolean findAndGotoExceptionHandler(ZThread thread,ZObject ex){
        while (true){
            ZFrame currentFrame = thread.getCurrentFrame();
            int pc = currentFrame.getNextPC()-1;
            // 从当前方法中寻找异常处理表，看是否可以找到匹配的 exception
            int handlerPC =  currentFrame.getMethod().findExceptionHandler(ex.getClazz(),pc);

            // 如果当前方法的exceptionTable能找到处理该异常的，那么就由该方法处理
            if(handlerPC>0){
                OperandStack operandStack = currentFrame.getOperandStack();
                operandStack.clear();
                operandStack.pushRef(ex);
                currentFrame.setNextPC(handlerPC);
                return true;
            }
            // 如果走到这一步，说明当前线程无法处理该异常,如果thread为空那么直接跳出循环
            thread.popFrame();
            if(thread.isStackEmpty()){
                return false;
            }

        }
    }

    //打印异常信息,包括调用栈的链;同时,此时虚拟机栈都已经清空了,所以整个JVM也就终止了;
    private void handleUncaughtException(ZThread thread, ZObject ex) {
        thread.clearStack();
        NStackTraceElement[] elements = (NStackTraceElement[]) ex.extra;
        for (int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }

    }

}
