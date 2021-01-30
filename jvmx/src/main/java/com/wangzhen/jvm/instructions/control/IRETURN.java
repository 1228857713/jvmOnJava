package com.wangzhen.jvm.instructions.control;

import com.wangzhen.jvm.instructions.base.NoOperandsInstruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;

/**
 * Description:  执行方法在执行结束后，如果有返回值，其返回值会放在该方法的操作数栈
 *                执行方法的外部——调用方法，需要将执行方法的返回值，压入调用方法的操作数栈
 * Datetime:    2020/9/28   8:40 下午
 * Author:   王震
 */
public class IRETURN extends NoOperandsInstruction {
    @Override
    public void execute(ZFrame frame) {
        ZThread thread = frame.getThread();
        ZFrame currentFrame = thread.popFrame();
        ZFrame invokeFrame = thread.getCurrentFrame();
        invokeFrame.getOperandStack().pushInt(currentFrame.getOperandStack().popInt());
    }
}
