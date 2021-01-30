package com.wangzhen.algorithm.leetcode.lt_70;

/**
 * Description: leetcode 第70道题目爬楼梯
 * Solution :   假设 n = 5，有5级楼梯要爬
 *              每次都有2种选择：爬1级或爬2级。
 *              如果爬1级，则剩下4级要爬。
 *              如果爬2级，则剩下3级要爬。
 *                 这分出了2个子问题：爬4级楼梯有几种方式？爬3级楼梯有几种方式？
 *                 爬 5 级楼梯的方式数 = 爬4级楼梯的方式数 + 爬3级楼梯的方式数
 *
 *               这里使用数组来保存计算结果，实现空间换时间
 * Datetime:    2020/9/8   8:34 上午
 * Author:   王震
 */
class Solution {
    public int climbStairs(int n) {
        int[] arrays =new int[n];
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        arrays[0]=1;
        arrays[1]=2;
        for (int i=2;i<n;i++){
            arrays[i]=arrays[i-1]+arrays[i-2];
        }
        return arrays[n-1];
    }
}
