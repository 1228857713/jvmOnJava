package com.wangzhen.algorithm.leetcode.lt_94;


import java.util.ArrayList;
import java.util.List;

/**
 * Description:  94. 二叉树的中序遍历
 * Datetime:    2020/9/14   4:39 下午
 * Author:   王震
 */
class Solution {
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return list ;
    }

    public void inorder(TreeNode node){
        if(node == null){
            return ;
        }
            inorder(node.left);
            list.add(node.val);
            inorder(node.right);
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
