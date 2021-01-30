package com.wangzhen.algorithm.leetcode.lt_06;

/**
 * Description:  桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
 * Datetime:    2020/11/22   1:10 下午
 * Author:   王震
 */
class Solution {
    public int minCount(int[] coins) {
        int sum  =0;
        for (int i = 0; i < coins.length; i++) {
            sum +=(int)Math.ceil(coins[i]*1.0/2);
        }
        return sum;
    }


}
