package com.wangzhen.algorithm.sort.day1;

import org.junit.Test;

/**
 * Description: 冒泡排序
 * Desc:  1.核心思路是每一遍排序将最大值排序到最后
 *        2.最大的值在最后，然后在排序前n-1 位即可
 * Datetime:    2021/1/25   下午3:50
 * Author:   王震
 */
public class BubbleSort {

    public  void sort(int []nums){
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length-i-1; j++) {
                if(nums[j+1]<nums[j]){
                    swap(nums,j,j+1);
                }
            }
        }
    }

    public void swap(int []nums,int i,int j){
        int temp=nums[i];
        nums[i] = nums[j];
        nums[j]=temp;
    }

    @Test
    public void test1(){
        int []array = {9,5,2,4,6,1,-1,6,0,33,43,22};
        sort(array);
        show(array);
    }

    public  void show(int []a){

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<a.length;i++){
            stringBuilder.append(","+a[i]);
        }
        System.out.println(stringBuilder);
    }
}

