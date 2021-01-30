package com.wangzhen.jvm.instructions.loads.loadref;


import com.wangzhen.jvm.instructions.base.Index8Instruction;
import com.wangzhen.jvm.instructions.loads.Load;
import com.wangzhen.jvm.runtimeData.ZFrame;

/**
 * Author: zhangxin
 * Time: 2017/5/5 0005.
 * Desc:
 */
public class ALOAD extends Index8Instruction {

    @Override
    public void execute(ZFrame frame) {
        Load.aLoad(frame,index);
    }
}
