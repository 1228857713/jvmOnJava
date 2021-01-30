package com.wangzhen.algorithm.leetcode.tree.lt_68_2;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

/**
 * Description: 剑指 Offer 68 - II. 二叉树的最近公共祖先
 * Datetime:    2020/9/20   2:28 下午
 * Author:   王震
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q)
            return root;
        TreeNode leftNode = lowestCommonAncestor(root.left,p,q);
        TreeNode rightNode = lowestCommonAncestor(root.right,p,q);
        if(leftNode==null){
            return rightNode;
        }
        if(rightNode==null){
            return leftNode;
        }
        return root;
    }
}
