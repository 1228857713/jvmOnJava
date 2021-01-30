package com.wangzhen.algorithm.leetcode.lt_1;

import java.util.HashMap;

/**
 * Description:  1. 两数之和
 * ans : 使用hash 表来降低查找速度
 * Datetime:    2020/9/28   9:45 下午
 * Author:   王震
 */
class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            if(map.containsKey(res)&&map.get(res)!=i){
                return new int[]{i,map.get(res)};
            }
        }
        return null;
    }
}