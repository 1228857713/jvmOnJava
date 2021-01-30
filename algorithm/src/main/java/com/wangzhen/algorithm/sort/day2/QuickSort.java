package com.wangzhen.algorithm.sort.day2;

import org.junit.Test;

/**
 * Description: 实现快速排序
 * Datetime:    2021/1/26   13:08
 * Author:   王震
 */
public class QuickSort {

    public void sort(int []array,int left, int right){
        if(left<right){
            int part = partition(array,left,right);
            sort(array,left,part-1);
            sort(array,part+1,right);
        }

    }
    public int partition(int []array, int left,int right){
        //以左边的值作为判断点
        int temp = left;
        int part = array[left];
        while (left<right){
            while (array[right]>=part&&left<right){
                right --;
            }
            while (array[left]<=part&&left<right){
                left++;
            }
            swap(array,left,right);
        }
        swap(array,temp,left);
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