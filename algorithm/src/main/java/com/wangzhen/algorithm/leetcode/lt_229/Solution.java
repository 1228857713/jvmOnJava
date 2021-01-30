package com.wangzhen.algorithm.leetcode.lt_229;

import java.util.*;

/**
 * Description:  229. 求众数 II 与169 题类似
 *              给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * Ans : 使用hashmap 计数
 * Datetime:    2020/9/21   11:19 下午
 * Author:   王震
 */
class Solution {
    public List<Integer> majorityElement(int[] nums) {

        ArrayList<Integer> ans = new ArrayList<>();
        if(nums==null||nums.length==0){
            return ans;
        }
        int candidate1 = nums[0],count1=0;
        int candidate2 = nums[0],count2=0;
        for (int num : nums) {
            if(candidate1==num){
                count1++;
                continue;
            }
            if(candidate2==num){
                count2++;
                continue;
            }
            // 如果 count1 已经是0个了 那么要替换候选者
            if(count1==0){
                candidate1=num;
                count1++;
                continue;
            }
            if(count2==0){
                candidate2=num;
                count2++;
                continue;
            }
            count1--;
            count2--;
        }
        int candidate1Num=0;
        int candidate2Num=0;
        for (int num : nums) {
            if(num==candidate1){
                candidate1Num++;
                continue;
            }
            if(num==candidate2) {
                candidate2Num++;
                continue;
            }
        }
        if(candidate1Num>nums.length/3){
            ans.add(candidate1);
        }

        if(candidate2Num>nums.length/3){
            ans.add(candidate2);
        }
        return ans;
    }
}
