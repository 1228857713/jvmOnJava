package com.wangzhen.algorithm.leetcode.tree.lt_144;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 二叉树的前序遍历，递归版本
 *              颜色标记法：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/
 * Datetime:    2020/9/24   8:55 下午
 * Author:   王震
 */
public class Solution {
    List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root==null){
            return list;
        }
        list.add(root.val);
        if(root.left!=null){
            preorderTraversal(root.left);
        }
        if(root.right!=null){
            preorderTraversal(root.right);
        }
        return list;
    }
}
