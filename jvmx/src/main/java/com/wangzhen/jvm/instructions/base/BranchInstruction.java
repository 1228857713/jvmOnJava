package com.wangzhen.jvm.instructions.base;



public abstract  class BranchInstruction implements Instruction {

    public int offset;
    @Override
    public void fetchOperands(ByteCodeReader codeReader) {
        offset = codeReader.readUint16();
    }

}
