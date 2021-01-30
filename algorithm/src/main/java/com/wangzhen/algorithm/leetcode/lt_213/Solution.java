package com.wangzhen.algorithm.leetcode.lt_213;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Description:
 * Datetime:    2020/11/26   9:53 下午
 * Author:   王震
 */
class Solution {
    public int rob(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        int []numsa = Arrays.copyOfRange(nums,0,nums.length-1);
        int []numsb =  Arrays.copyOfRange(nums,1,nums.length);
        return Math.max(robMax(numsa),robMax(numsb));
    }
    public int robMax(int[] nums) {
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
