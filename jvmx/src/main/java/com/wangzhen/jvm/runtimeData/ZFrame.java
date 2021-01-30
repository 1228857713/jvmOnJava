package com.wangzhen.jvm.runtimeData;

import com.wangzhen.jvm.runtimeData.helap.ZMethod;

/**
 * jvm 虚拟机栈帧：
 *            1.局部变量表
 *            2.操作数栈
 *            3.动态链接
 *            4.方法返回地址
 *            5.一些附加信息
 */
public class ZFrame {
    LocalVars localVars;    //局部变量表的引用;
    OperandStack operandStack;  //操作数栈的引用;

    //当前栈帧所在的线程;
    ZThread thread;
    // 当前栈帧所执行的方法
    ZMethod method;

    //frame中并不改变PC的值,
    // 这个next pc 主要是指的就是frame 在读取 code 的时候的游标，用于记录已经读取到哪个值了。
    int nextPC;

    public ZFrame(ZThread thread, int maxLocal, int maxStack) {
        localVars = new LocalVars(maxLocal);
        operandStack = new OperandStack(maxStack);
        this.thread = thread;
    }

    public ZFrame(ZThread thread, ZMethod method) {
        this.thread = thread;
        this.method = method;
        localVars = new LocalVars(method.getMaxLocals());
        operandStack = new OperandStack(method.getMaxStack());
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars = localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(OperandStack operandStack) {
        this.operandStack = operandStack;
    }

    public ZThread getThread() {
        return thread;
    }

    public void setThread(ZThread thread) {
        this.thread = thread;
    }

    public ZMethod getMethod() {
        return method;
    }

    public void setMethod(ZMethod method) {
        this.method = method;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public  void revertPc(){
        this.nextPC = thread.getPc();
    }

    @Override
    public String toString() {
        return method+"";
    }
}
