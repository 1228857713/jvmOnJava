package com.wangzhen.algorithm.sort.day1;

import org.junit.Test;

/**
 * Description: 快速排序
 * desc: 排序中的快速排序，主要使用分治思想
 * Datetime:    2021/1/25   16:29
 * Author:   王震
 */
public class QuickSort {
    public  void sort(int []nums,int left ,int right){
        if(right>left){
            int partion = partition(nums,left,right);
            sort(nums,left,partion-1);
            sort(nums,partion+1,right);
        }
    }
    public int partition(int []nums,int left ,int right){
        int part = nums[left];
        int temp = left;
        while (left<right){
            while (left<right&&nums[right]>=part){
                right--;
            }
            while (left<right&&nums[left]<=part){
                left++;
            }
            swap(nums,left,right);
        }
        swap(nums,temp,left);
        return left;
    }


    public void swap(int []nums,int i,int j){
        int temp=nums[i];
        nums[i] = nums[j];
        nums[j]=temp;
    }

    @Test
    public void test1(){
        int []array = {9,5,2,4,6,1,-1,6,0,33,43,22};
        sort(array,0,array.length-1);
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