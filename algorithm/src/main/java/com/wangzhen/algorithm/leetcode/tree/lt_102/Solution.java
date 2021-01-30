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
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        int high = height(root);
        List<Integer> []listArray = new List[high];
        for (int i = 0; i < listArray.length; i++) {
            listArray[i]=new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode n=queue.poll();
            listArray[high-height(n)].add(n.val);
            if(n.left!=null)
                queue.add(n.left);
            if(n.right!=null)
                queue.add(n.right  );
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < listArray.length; i++) {
            ans.add(listArray[i]);
        }
        return ans;
    }





    // 二叉树根节点的深度
    public int height(TreeNode p){
        if(p==null)
            return 0;
        return Math.max(height(p.left),height(p.right))+1;
    }
}
