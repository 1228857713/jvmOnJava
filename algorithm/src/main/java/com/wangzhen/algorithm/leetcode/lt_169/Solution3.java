package com.wangzhen.algorithm.leetcode.lt_169;

/**
 * Description: 169. 多数元素
 * ans : 解决方案使用 摩尔投票法
 * Datetime:    2020/9/21   10:59 下午
 * Author:   王震
 */
class Solution3 {
    public int majorityElement(int[] nums) {
        int ans=nums[0];
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==ans){
                count++;
            }else {
               if(count==0){
                   ans = nums[i];
                   count=1;
               }else {
                   count--;
               }
            }
        }
        int j=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==ans)
                j++;
        }
        if(j>(nums.length/2)){
            return ans;
        }
        return Integer.MIN_VALUE;
    }
}
