package com.wangzhen.algorithm.leetcode.lt_509;

/**
 * Description: 斐波那契数
 * Datetime:    2020/9/7   1:39 下午
 * Author:   王震
 */
public class Solution {
    public int fib(int N) {
      return fibonacci(N );
    }
    public int fibonacci(int n){
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        return fibonacci(n-1)+fibonacci(n-2);
    }
}
