package com.wangzhen.algorithm.leetcode.lt_674;

/**
 * Description: 最长连续递增序列
 * Datetime:    2021/1/25   下午3:36
 * Author:   王震
 */
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length<=1){
            return nums.length;
        }
        int count = 1;
        int ans = 1;
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i+1]>nums[i]){
                count = count +1;
                ans = Math.max(ans,count);
            }else {
                count =1;
            }

        }
        return ans;
    }
}
