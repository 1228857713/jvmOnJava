package com.wangzhen.algorithm.leetcode.lt_344;

/**
 * Description: 344. 反转字符串
 * Datetime:    2020/10/24   5:16 下午
 * Author:   王震
 */
class Solution {
    public void reverseString(char[] s) {
        if(s.length<2){
            return;
        }
        for (int i = 0; i < s.length/2; i++) {
            swap(s,i,s.length-i-1);
        }
    }

    public void swap(char []s ,int i,int j){
        char temp;
        temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
