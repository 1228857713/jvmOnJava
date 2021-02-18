package com.wangzhen.algorithm.leetcode.lt_239;

/**
 * Description: https://leetcode-cn.com/problems/sliding-window-maximum/
 * Datetime:    2021/2/18   下午3:53
 * Author:   王震
 */
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int [] result = new int[nums.length-k+1];
        for (int i = 0; i < result.length; i++) {
            result[i] = maxNum(nums,i,i+k);
        }
        return result;
    }


    /**
     * @desc 求最大值
     * @param nums
     * @return
     */
    public int maxNum(int  []nums,int start ,int end){
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if(nums[i]>max)
                max = nums[i];
        }
        return max;
    }

}
