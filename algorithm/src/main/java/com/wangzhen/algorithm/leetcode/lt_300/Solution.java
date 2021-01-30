package com.wangzhen.algorithm.leetcode.lt_300;

import java.util.Arrays;

/**
 * Description:  300. 最长上升子序列
 * Datetime:    2020/9/16   1:15 下午
 * Author:   王震
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len<2){
            return len;
        }


        int[] dp = new int[len];
        Arrays.fill(dp,1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j]<nums[i]){
                    // 这里是为了找到 nums[j] 里面的最大值
                     dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        // 寻找 dp[i] 中的最大值
        int max=1;
        for (int i = 0; i < len; i++) {
             max = Math.max(max,dp[i]);
        }

        return max;
    }
}
