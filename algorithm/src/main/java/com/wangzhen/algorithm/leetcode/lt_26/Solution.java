package com.wangzhen.algorithm.leetcode.lt_26;

/**
 * Description: 26. 删除排序数组中的重复项
 * ans: 使用双指针法来解决这个问题，快指针 遍历这个数组，如果发现和慢指针的值不一样就使得慢指针加一位并且赋值给他
 *      这是因为是排序数组所以，所以重复的数字都并列在一块
 * Datetime:    2020/10/4   11:30 上午
 * Author:   王震
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int []array={1,1,2};
        System.out.println(solution.removeDuplicates(array));

    }
    public int removeDuplicates(int[] nums) {
        int slow=0;
        //int fast;
        for (int fast = 1; fast < nums.length; fast++) {
            if(nums[slow]!=nums[fast]){
                slow++;
                nums[slow]=nums[fast];
            }
        }
        return  slow+1;
    }
}
