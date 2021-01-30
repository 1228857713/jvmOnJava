package com.wangzhen.algorithm.leetcode.tree.lt_27;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

/**
 * Description:   二叉树的镜像
 * Datetime:    2020/9/17   6:09 下午
 * Author:   王震
 */
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }
        lastOrder(root);
        return root;
    }
    public void lastOrder(TreeNode t){
        if(t.left!=null){
            lastOrder(t.left);
        }
        if(t.right!=null){
            lastOrder(t.right);
        }
        TreeNode temp = t.left;
        t.left = t.right;
        t.right = temp;
    }
}