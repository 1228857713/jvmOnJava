package com.wangzhen.jvm.znative.java.lang;


import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZMethod;
import com.wangzhen.jvm.runtimeData.helap.ZObject;
import com.wangzhen.jvm.znative.NativeMethod;

/**
 *
 */
public class Nthrowable {
    /**
     * 这个方法是任何Exception的父类的构造方法中,调用的本地方法
     */
    public static class fillInStackTrace implements NativeMethod {

        @Override
        public void run(ZFrame frame) {
            // throw创建的Exception实例;
            ZObject self = frame.getLocalVars().getRef(0);
            //将上面创建的Exception放到当前frame的操作数栈
            frame.getOperandStack().pushRef(self);
            // Java 虚拟机栈信息
            self.extra = createStackTraceElements(self, frame.getThread());
        }

        //创建异常调用链,传入的第一个参数为throw 抛出的exception
        private NStackTraceElement[] createStackTraceElements(ZObject exObj, ZThread thread) {
            //不能直接从当前的frame开始记录,起码在栈顶的两帧正在执行fillInStackTrace(int)和fillInStackTrace()方法
            //同时在这两帧的下面几帧正在执行Exception的构造方法,所以也需要跳过;具体跳过几帧,则由下面的distanceToObject方法来计算
            int skip = distanceToObject(exObj.getClazz()) + 2;

            //拿栈帧,应该是拿前面的部分吧;怎么拿后面的部分?不是先入栈的在数组的前面吗?
            //问题出在GetFrames这个方法上,该方法使用stack的方式从栈顶逐渐拿的数据
            //注意:本地变量表和操作数栈的底层是数组;而栈帧的底层是单向链表,不是数组!!!
            ZFrame[] frames = thread.getFrames();
            NStackTraceElement[] stes = new NStackTraceElement[frames.length - skip];
            for (int i = skip; i < frames.length; i++) {
                stes[i - skip] = createStackTraceElement(frames[i]);
            }
            return stes;
        }

        private int distanceToObject(ZClass exClazz) {
            int distance = 0;
            //通过计算其继承层级来计算需要执行几个构造方法;
            for (ZClass c = exClazz.getSuperClass(); c != null; c = c.getSuperClass()) {
                distance++;
            }
            return distance;
        }

        //根据每一帧,拿到对应的消息;
        private NStackTraceElement createStackTraceElement(ZFrame frame) {
            ZMethod method = frame.getMethod();
            ZClass clazz = method.getClazz();
            return new NStackTraceElement(clazz.getSourceFile(), clazz.getJavaName(), method.getName(), method.getLineNumber(frame.getNextPC() - 1));
        }
    }
}
