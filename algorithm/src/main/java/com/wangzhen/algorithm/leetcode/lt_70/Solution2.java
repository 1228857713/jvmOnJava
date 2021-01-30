package com.wangzhen.algorithm.leetcode.lt_70;

/**
 * Description:
 * Datetime:    2020/9/8   8:48 上午
 * Author:   王震
 */
public class Solution2 {
    public int climbStairs(int n) {
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }
}
