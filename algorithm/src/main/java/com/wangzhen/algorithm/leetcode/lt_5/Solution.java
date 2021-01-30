package com.wangzhen.algorithm.leetcode.lt_5;



/**
 * Description: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * ans :动态规划没有看懂，这里实现一下中心扩展法
 * Datetime:    2020/11/25   5:28 下午
 * Author:   王震
 */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("babad"));
    }
    public String longestPalindrome(String s) {
        int left=0,right = 0;
        char [] array = s.toCharArray();
        int post=0,maxlen=1;
        for (int i = 1; i < array.length-1; i++) {
            int odd = oddPalindrome(array,i);
            int even = evenPalindrome(array,i,i+1);
            int max = odd>even ? odd :even;
            if(max>maxlen){
                maxlen=max;
                post=i;
            }
        }
        //偶数
        if(maxlen%2==0){
            int start = post-(maxlen/2-1);
            int end = post +(maxlen/2);
            String s1 = new String(array, start, end);
            return s1;
        }else {// 奇数
            int start = post -(maxlen/2);
            int end = post +(maxlen/2);
            String s1 = new String(array,start,end);
            return s1;

        }

    }
    //奇数
    public int oddPalindrome(char [] array,int i){
        int left,right;
       for(left=i-1,right=i+1;left>0&&right<array.length;left--,right++){
                if(array[left]!=array[right]){
                    left++;
                    right--;
                    break;
                }
       }
       int maxLength = right-left;
       return maxLength;
    }

    //偶数
    public int evenPalindrome(char [] array,int i,int j){
        if(array[i]!=array[j]){
            return 0;
        }
        int left,right;
        for(left=i,right=j;left>0&&right<array.length;left--,right++){
            if(array[left]!=array[right]){
                break;
            }
        }
        int maxLength = right-left;
        return maxLength;
    }
}
