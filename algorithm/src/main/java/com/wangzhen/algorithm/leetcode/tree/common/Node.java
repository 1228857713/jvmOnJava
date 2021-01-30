package com.wangzhen.algorithm.leetcode.tree.common;

/**
 * Description:
 * Datetime:    2020/9/18   11:07 上午
 * Author:   王震
 */
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
