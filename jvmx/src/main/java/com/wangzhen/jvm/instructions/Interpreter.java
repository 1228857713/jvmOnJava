package com.wangzhen.jvm.instructions;


import com.wangzhen.jvm.instructions.base.ByteCodeReader;
import com.wangzhen.jvm.instructions.base.Instruction;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;




public class Interpreter {

    public static void loop(ZThread thread) throws NoSuchMethodException {

        ByteCodeReader reader = new ByteCodeReader();
        while (true){
            ZFrame frame ;
            try{
               frame = thread.getCurrentFrame();
            }catch (Exception e){
                System.out.println("当前线程已经执行完所有jvm栈退出");
                break;
            }

            // 获得当前栈帧的方法的字节码
            byte [] code = frame.getMethod().getCode();
            //这第一次 frame 才刚初始化，获取的 pc 应该是默认值 0 吧。
            int pc = frame.getNextPC();
            thread.setPc(pc);
            //reset 方法，其实是在不断的设置 pc 的位置。
            reader.reset(code,pc);
            int opCode = reader.readUint8();
            //解析指令,创建指令,然后根据不同的指令执行不同的操作
            Instruction instruction = InstructionFactory.createInstruction(opCode);
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            System.out.println("执行指令"+Integer.toHexString(opCode)+"："+instruction.toString()+"----栈桢为"+frame.toString());
            instruction.execute(frame);
        }
    }
}
