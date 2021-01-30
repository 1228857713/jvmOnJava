package com.wangzhen.algorithm.leetcode.tree.lt_111;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

/**
 * Description: 111. 二叉树的最小深度
 * Datetime:    2020/10/4   12:15 下午
 * Author:   王震
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root==null)
            return 0;
        else if(root.left==null){
            return minDepth(root.right)+1;
        }else if(root.right==null){
            return minDepth(root.left)+1;
        }else{
            return  Math.min(minDepth(root.left),minDepth(root.right))+1;
        }

    }
}
