package com.wangzhen.algorithm.leetcode.lt_229;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:  229. 求众数 II 与169 题类似
 *              给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * Ans : 使用hashmap 计数
 * Datetime:    2020/9/21   11:19 下午
 * Author:   王震
 */
class Solution2 {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        // 使用hashmap 对数组元素个数计数
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else {
               map.put(nums[i],1);
            }
        }
        // 此题没有写完
        return null;
    }
}
