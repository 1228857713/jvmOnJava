package com.wangzhen.algorithm.leetcode.lt_3;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * Datetime:    2020/11/24   8:26 下午
 * Author:   王震
 */
public class Solution {
    Map<Character,Integer> map ;
    public int lengthOfLongestSubstring(String s) {
        map = new HashMap<>(s.length());

        int left=0,right = 0;
        // 最长的子字符串长度
        int ans = 0;
        while (right<s.length()){
            char c = s.charAt(right);
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
            right++;

            // 如果右移动的过程中字符串重复了
            while (map.get(c)>1){
                char b = s.charAt(left);
                map.put(b,map.get(b)-1);
                left++;
            }
            ans = Math.max(ans,right-left);

        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }
}
