package com.wangzhen.algorithm.leetcode.tree.lt_102;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Description: 102. 二叉树的层序遍历
 * Datetime:    2020/9/17   10:21 下午
 * Author:   王震
 */
class Solution2 {
    public List<List<Integer>> levelOrder(TreeNode root) {


        List<List<Integer>> lists = new ArrayList<>();
        if(root==null){
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()){
            // 得到当前的 queue 的数量的个数
            int currentsize = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < currentsize; i++) {
                TreeNode n=queue.poll();
                list.add(n.val);
               if(n.left!=null){
                   queue.add(n.left);
               }
               if(n.right!=null){
                    queue.add(n.right  );
                }
            }
            lists.add(list);
        }

        return lists;
    }
}
