package com.wangzhen.algorithm.leetcode.lt_1;

/**
 * Description:  1. 两数之和
 * ans : 暴力解法
 * Datetime:    2020/9/28   9:45 下午
 * Author:   王震
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int [] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if(nums[i]+nums[j]==target){
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return null;
    }
}