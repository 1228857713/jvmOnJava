package com.wangzhen.algorithm.leetcode.tree.lt_100;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

/**
 * Description:
 * Datetime:    2020/9/17   10:13 下午
 * Author:   王震
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null)
            return true;
        if(p==null||q==null||p.val!=q.val)
            return false;
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}
