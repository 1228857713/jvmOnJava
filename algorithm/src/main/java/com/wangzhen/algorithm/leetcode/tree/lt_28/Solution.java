package com.wangzhen.algorithm.leetcode.tree.lt_28;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

/**
 * Description:  对称的二叉树
 * Datetime:    2020/9/17   6:08 下午
 * Author:   王震
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return recure(root.left,root.right);
    }
    public boolean recure(TreeNode L,TreeNode R){
        if(L==null&&R==null)
            return true;
        if(L==null||R==null||L.val!=R.val) return false;
        return recure(L.left,R.right)&&recure(L.right,R.left);
    }

}
