package com.wangzhen.algorithm.leetcode.lt_198;

/**
 * Description: 打家劫舍
 * Datetime:    2020/11/26   8:59 下午
 * Author:   王震
 */
class Solution {
    int []dpTable;
    public int rob(int[] nums) {
        dpTable = new int[nums.length];
        for (int i = 0; i < dpTable.length; i++) {
            dpTable[i] = -1;
        }
        return robMax(nums,0);
    }

    public int robMax(int []nums,int i){

        if(i>nums.length-1)
            return 0;
        if(dpTable[i]!=-1){
            return dpTable[i];
        }
        int doRob = nums[i]+ robMax(nums,i+2);
        int noRob = robMax(nums,i+1);

        int max =  Math.max(doRob,noRob);
        dpTable[i] = max;
        return max;
    }
}
