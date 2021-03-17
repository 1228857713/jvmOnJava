package com.wangzhen.jvm.runtimeData;


import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 *  jvm 虚拟机栈
 */
public class ZStack {
    // 虚拟机栈中包含的最大的栈帧的容量
    int maxSize;

    // 使用ArrayList 存放栈帧比较简单
    private List<ZFrame> frames = new ArrayList<ZFrame>();

    public ZStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(ZFrame zFrame){

        if(frames.size()>maxSize){
            throw new StackOverflowError("栈溢出异常");
        }
        frames.add(zFrame);

    }
    public ZFrame pop(){
        if(frames.size()==0){
            throw new EmptyStackException();
        }
        ZFrame popjFrame = frames.remove(frames.size()-1);
        return popjFrame;
    }
    public ZFrame top(){
        if(frames.size()==0){
            throw new EmptyStackException();
        }
        return frames.get(frames.size()-1);
    }

    public boolean isEmpty(){
        return frames.size()==0;
    }

    public List<ZFrame> getFrames() {
        return frames;
    }
}
