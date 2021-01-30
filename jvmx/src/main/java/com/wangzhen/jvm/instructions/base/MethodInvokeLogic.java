package com.wangzhen.jvm.instructions.base;

import com.wangzhen.jvm.runtimeData.Slot;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;
import com.wangzhen.jvm.runtimeData.helap.ZMethod;

public class MethodInvokeLogic {
    public static void invokeMethod(ZFrame invokeFrame, ZMethod method){
        // 根据方法创建frame 压入 当前的线程的栈帧
        ZThread thread = invokeFrame.getThread();
        ZFrame newframe = thread.createFrame(method);
        thread.pushFrame(newframe);
        int argClout = method.getArgSlotCount();
        if(argClout>0){
            for (int i=0;i<argClout;i++){
                Slot slot =  invokeFrame.getOperandStack().popSlot();
                newframe.getLocalVars().setSlot(i,slot);
            }
        }


    }
}
