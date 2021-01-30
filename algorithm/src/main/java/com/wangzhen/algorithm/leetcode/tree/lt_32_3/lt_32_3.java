package com.wangzhen.algorithm.leetcode.tree.lt_32_3;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

import java.util.*;

/**
 * Description:  剑指 Offer 32 - III. 从上到下打印二叉树 III
 * Datetime:    2020/9/17   11:47 下午
 * Author:   王震
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root==null){
            return lists;
        }
        Queue<TreeNode> queue= new LinkedList();
        int num =1;
        queue.add(root);
        while (!queue.isEmpty()){
            int currentSize = queue.size();

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < currentSize; i++) {

                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            // 如果num 是偶数那么翻转列表
            if(num%2==0){
                Collections.reverse(list);
            }
            lists.add(list);
            num++;
        }
        return lists;
    }
}
