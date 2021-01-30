package com.wangzhen.algorithm.leetcode.lt_322;



import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Description: 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * Datetime:    2020/9/10   10:53 下午
 * Author:   王震
 */
class Solution {
    // DpTable
    Map<Integer,Integer> map =null;
    public static void main(String[] args) {
        Solution solution = new Solution();
        int []coins = {1,2,5};
        System.out.println(solution.coinChange(coins, 9));

    }
    public int coinChange(int[] coins, int sum) {
        map = new HashMap(sum);
        int reslult = minCoinNums(coins,sum);
        if(reslult<0){
            return -1;
        }else {
            return reslult;
        }

    }

    public int minCoinNums(int []coins,int sum){
        if(map.containsKey(sum)){
            return map.get(sum);
        }
        if(sum<0){
            return -1;
        }
        if(sum==0){
            return 0;
        }
        int []array = new int[coins.length];
        for (int i = 0; i < coins.length; i++) {
            array[i]=minCoinNums(coins,sum-coins[i]);
        }
        int result =min(array);
        if(result==Integer.MAX_VALUE){
            map.put(sum,-1);
            return -1;
        }
        int ans = result+1;
        map.put(sum,ans);
        return ans;
    }
    public int min(int []arr){
        int result=Integer.MAX_VALUE;
        for (int i : arr) {
            // 这个判断很重要，因为如果不存在解法我们会把他的结果重置为-1。这时候在最小值比较中不能把它带入其中
            // 如果都是小于-1的 那么就直接 返回最大整数值
            if(i>=0){
                result = Math.min(result,i);
            }
        }
        return result;
    }


}
