package com.wangzhen.algorithm.leetcode.tree.lt_543;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

import java.util.HashMap;

/**
 * Description: 543. 二叉树的直径
 * ans: 使用递归的方法，当是我把问题想复杂了，其实就是 左子树的深度加上右子树的深度的 最大值
 * Datetime:    2020/9/30   11:00 上午
 * Author:   王震
 */
class Solution {
    HashMap<TreeNode,Integer> map = new HashMap<>();

    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        int rootDepth = treeLength(root.left)+ treeLength(root.right)+2;
        return Math.max(Math.max(leftDiameter,rightDiameter),rootDepth);
    }

    // 某一个节点的深度
    public int treeLength(TreeNode root){
        if(map.containsKey(root))
            return map.get(root);
        if(root == null){
            return -1;
        }else {
            int length =  Math.max(treeLength(root.left), treeLength(root.right))+1;
            map.put(root,length);
            return length;
        }

    }

}

