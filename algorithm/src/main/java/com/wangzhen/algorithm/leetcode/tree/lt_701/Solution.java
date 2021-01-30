package com.wangzhen.algorithm.leetcode.tree.lt_701;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

/**
 * Description: 701. 二叉搜索树中的插入操作
 * Datetime:    2020/9/30   10:45 上午
 * Author:   王震
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node  = new TreeNode(val);
        if(root == null)
            return node;

        if(val<root.val){
            if(root.left!=null){
                insertIntoBST(root.left,val);
            }else {
                root.left = node;
            }
        }else {
            if(root.right!=null){
                insertIntoBST(root.right,val);
            }else {
                root.right = node;
            }
        };
        return root;
    }


}
