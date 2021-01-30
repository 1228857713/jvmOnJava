package com.wangzhen.jvm.znative.java.lang;

/**
 * Description:  jvm 虚拟机的栈信息
 * Datetime:    2020/9/28   8:48 下午
 * Author:   王震
 */

public class NStackTraceElement {
    String fileName;//类所在的java文件
    String className;//声明方法的类名
    String methodName;//调用方法名
    int lineNumber;//出现exception的行号

    public NStackTraceElement(String fileName, String className, String methodName, int lineNumber) {
        this.fileName = fileName;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return className + "." + methodName + "(" + fileName + ":" + lineNumber + ")";
    }
}
