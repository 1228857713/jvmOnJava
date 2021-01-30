package com.wangzhen.algorithm.leetcode.lt_169;

/**
 * Description: 169. 多数元素
 * ans : 解决方案使用 摩尔投票法
 * Datetime:    2020/9/21   10:59 下午
 * Author:   王震
 */
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count=0;
        for (int num : nums) {
            if(count==0){
                candidate = num;
            }
            count += (num==candidate)? 1 : -1;
        }
        return candidate;
    }
}
