package com.wangzhen.algorithm.leetcode.tree.lt_55;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

/**
 * Description:  输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度
 * Datetime:    2020/9/13   3:00 下午
 * Author:   王震
 */
class Solution2 {
    public int maxDepth(TreeNode root) {

        return root==null?0:Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}




