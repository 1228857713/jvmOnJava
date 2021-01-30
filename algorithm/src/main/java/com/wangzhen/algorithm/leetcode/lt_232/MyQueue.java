package com.wangzhen.algorithm.leetcode.lt_232;

import java.util.Stack;

/**
 * Description: 232. 用栈实现队列
 * ans : 用双栈实现 队列
 * Datetime:    2020/9/28   10:01 下午
 * Author:   王震
 */
class MyQueue {

    // 输入栈
    Stack<Integer> sin;
    // 输出栈
    Stack<Integer> sout;
    /** Initialize your data structure here. */
    public MyQueue() {
        sin = new Stack();
        sout = new Stack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        sin.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!sout.isEmpty()){
            return sout.pop();
        }

        // 如果是 输出栈为空的话，那么把输入栈里面的所有数据都放入到输出栈
        while (!sin.isEmpty()){
            sout.push(sin.pop());
        }
        return sout.pop();
    }

    /** Get the front element. */
    public int peek() {
        int peak = pop();
        sout.push(peak);
        return peak;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return sin.isEmpty()&&sout.isEmpty();
    }
}
