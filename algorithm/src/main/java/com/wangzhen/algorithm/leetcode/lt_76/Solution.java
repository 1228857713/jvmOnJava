package com.wangzhen.algorithm.leetcode.lt_76;

/**
 * Description:
 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。


 示例 1：
 输入：s = "ADOBECODEBANC", t = "ABC"
 输出："BANC"
 示例 2：
 输入：s = "a", t = "a"
 输出："a"

 * Datetime:    2020/11/24   8:56 上午
 * Author:   王震
 */
class Solution {
    public String minWindow(String s, String t) {
        char [] s1 = s.toCharArray();
        char [] s2 = t.toCharArray();
        int left = 0;
        int right = s1.length-1;
        while (left<right){
            while (right>left){
                if(contain(s1,s2,left,right)){
                    right--;
                }else {
                    break;
                }
            }
            left++;
        }

        return "";
    }

    public boolean contain(char [] s1,char []s2,int left,int right){
        for (int i = 0; i < s2.length; i++) {
            boolean isInclude = false;
            for (int j = 0; j < s1.length; j++) {
                if(s1[j]==s2[i]){
                    isInclude = true;
                    break;
                }
            }
            if(isInclude==false)
                return false;
        }
        return true;
    }
}
