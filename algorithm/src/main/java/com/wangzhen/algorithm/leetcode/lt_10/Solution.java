package com.wangzhen.algorithm.leetcode.lt_10;



/**
 * Description:  剑指 Offer 10- I. 斐波那契数列
 * Datetime:    2020/9/22   12:18 上午
 * Author:   王震
 */
class Solution {
    public int fib(int n) {
        if(n<2)
            return n;
        int[] nums = new int[n+1];
        nums[0]=0;
        nums[1]=1;
        for (int i = 2; i <nums.length; i++) {
            nums[i]=nums[i-1]+nums[i-2];
            nums[i]=nums[i]%1000000007;
        }
        return nums[n];
    }


}
