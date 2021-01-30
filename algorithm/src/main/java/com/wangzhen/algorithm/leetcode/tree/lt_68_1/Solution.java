package com.wangzhen.algorithm.leetcode.tree.lt_68_1;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

/**
 * Description: 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * Datetime:    2020/9/20   3:41 下午
 * Author:   王震
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val==p.val||root.val==q.val)
            return root;
        if(p.val<root.val&&q.val<root.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        if(p.val>root.val&&q.val>root.val){
           return lowestCommonAncestor(root.right,p,q);
        }

        return root;
    }
}
