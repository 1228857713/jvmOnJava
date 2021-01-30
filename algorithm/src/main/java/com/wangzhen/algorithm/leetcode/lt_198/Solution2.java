package com.wangzhen.algorithm.leetcode.lt_198;

/**
 * Description: 打家劫舍
 * Datetime:    2020/11/26   8:59 下午
 * Author:   王震
 */
class Solution2 {

    public int rob(int[] nums) {
        int []dpTable = new int[nums.length+2];
        for (int i = 0; i < dpTable.length; i++) {
            dpTable[i]=0;
        }
        for (int i = nums.length-1; i >=0; i--) {
                int doRob = nums[i]+dpTable[i+2];
                int noRob = dpTable[i+1];
                dpTable [i] = Math.max(doRob,noRob);
        }
        return dpTable[0];
    }


}
