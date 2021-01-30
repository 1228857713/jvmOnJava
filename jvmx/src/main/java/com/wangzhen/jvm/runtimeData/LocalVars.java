package com.wangzhen.jvm.runtimeData;


/**
 • 局部变量表：
      • 定义为一个数字数组，主要用来存储方法参数和定义的变量，包括基本数据类型和对象引用，以及returnAddress类型
      • 由于局部变量表是建立在栈帧上，是每个线程私有的，所有不存在线程安全问题。
      • 局部变量表的大小是在编译器决定的，在方法运行期间不会改变其大小

 */
public class LocalVars extends Slots{

    public LocalVars(int size) {
        super(size);
    }

}
