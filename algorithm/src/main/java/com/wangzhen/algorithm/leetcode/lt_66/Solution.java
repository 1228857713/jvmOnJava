package com.wangzhen.algorithm.leetcode.lt_66;

/**
 * Description: 66. 加一
 * Datetime:    2020/10/3   6:28 下午
 * Author:   王震
 */
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >=0 ; i--) {
            digits[i]++;
            if(digits[i]%10!=0){
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[digits.length+1];
        digits[0] =1;
        return  digits;
    }
}
