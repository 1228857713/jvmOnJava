package com.wangzhen.algorithm.leetcode.tree.lt_543;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

import java.util.HashMap;

/**
 * Description: 543. 二叉树的直径
 * ans: 使用递归的方法，当是我把问题想复杂了
 * Datetime:    2020/9/30   11:00 上午
 * Author:   王震
 */
class Solution2 {

    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        if(root!=null)
            ans = 0;
        treeLength(root);
        return ans;
    }

    // 某一个节点的深度
    public int treeLength(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftLength = treeLength(root.left);
        int rightLength = treeLength(root.right);
        ans = Math.max(ans,leftLength+rightLength);
        return Math.max(leftLength,rightLength)+1;

    }

}

