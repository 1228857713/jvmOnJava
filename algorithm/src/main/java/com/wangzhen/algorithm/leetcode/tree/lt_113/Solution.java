package com.wangzhen.algorithm.leetcode.tree.lt_113;

import com.wangzhen.algorithm.leetcode.tree.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:  解题失败。
 * Datetime:    2020/12/8   下午7:49
 * Author:   王震
 */
public class Solution {
    List<Integer> result;
    List<List<Integer>> results;
    int end;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        results = new ArrayList<>();
        result = new ArrayList<>();
        end = sum;
        order(root,sum);
        return results;
    }
    //中序遍历
    public void order(TreeNode root,int sum){
        if(root==null)
            return;
        sum = sum - root.val;

        if(sum==0){
            result.add(root.val);
            results.add(result);
            result = new ArrayList<>();
            sum=end;
            return;
        }else if(sum<0){
            result = new ArrayList<>();
            sum= end;
            return;
        }
        result.add(root.val);
        order(root.left,sum);
        order(root.right,sum);
    }

}
