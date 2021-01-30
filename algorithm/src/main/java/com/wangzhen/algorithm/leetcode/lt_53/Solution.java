package com.wangzhen.algorithm.leetcode.lt_53;

/**
 * Description: leetcode 第53题 最大子序和
 * 解法说明   ：
 * Datetime:    2020/9/9   9:20 下午
 * Author:   王震
 */
public class Solution {

    public int maxSubArray(int[] nums) {
        int ans=Integer.MIN_VALUE;
        int sum =0;
        for (int num:nums){
            if(sum>0){
                sum +=num;
            }else{
                sum = num;
            }
            ans = Math.max(sum,ans);
        }
        return ans;
    }


}
