package com.wangzhen.jvm.instructions.references;

import com.wangzhen.jvm.instructions.base.Index16Instruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZMethod;


/**
 * Author: zhangxin
 * Time: 2017/7/26.
 * Desc:获取静态变量的值，将其值放在操作数栈中
 */
public class GET_STATIC extends Index16Instruction {
    @Override
    public void execute(ZFrame frame) {
        ZMethod currentMethod = frame.getMethod();
       // ZClass
    }
}
