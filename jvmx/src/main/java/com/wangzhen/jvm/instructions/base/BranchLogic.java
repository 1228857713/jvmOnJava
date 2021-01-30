package com.wangzhen.jvm.instructions.base;

import com.wangzhen.jvm.runtimeData.ZFrame;

public class BranchLogic {
    public static void branch(ZFrame frame,int offset){
        int pc = frame.getThread().getPc();
        int next_pc = pc+offset;
        frame.setNextPC(next_pc);
    }

}
