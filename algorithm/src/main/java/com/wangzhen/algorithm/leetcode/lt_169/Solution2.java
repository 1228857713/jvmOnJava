package com.wangzhen.algorithm.leetcode.lt_169;

import java.util.Arrays;

/**
 * Description:
 * Datetime:    2020/9/21   11:00 下午
 * Author:   王震
 */
public class Solution2 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
