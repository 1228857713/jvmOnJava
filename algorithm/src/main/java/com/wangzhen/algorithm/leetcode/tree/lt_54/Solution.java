package com.wangzhen.algorithm.leetcode.tree.lt_54;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 二叉树中序遍历
 * Datetime:    2020/9/20   3:09 下午
 * Author:   王震
 */
class Solution {
    List<Integer> list = new ArrayList<Integer>();
    public int kthLargest(TreeNode root, int k) {
        if(k<1)
            return -1;
        midOrder(root);
        return list.get(list.size()-k);
    }
    public void midOrder(TreeNode root){
        if(root==null)
            return ;
        midOrder(root.left);
        list.add(root.val);
        midOrder(root.right);
    }
}
