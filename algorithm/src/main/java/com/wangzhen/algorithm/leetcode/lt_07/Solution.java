package com.wangzhen.algorithm.leetcode.lt_07;

/**
 * Description:
 * Datetime:    2020/9/14   7:22 下午
 * Author:   王震
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return null;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public int height(){
        return Math.max(this.left==null ? 0 : left.height(),this.right==null ? 0 :right.height())+1;
    }
    TreeNode(int x) {
        val = x;
    }
}
