package com.wangzhen.algorithm.leetcode.lt_215;

import java.util.Random;

/**
 * Description: 基于快速排序来实现
 * desc :       不需要完全实现快速排序
 * Datetime:    2020/9/28   9:23 下午
 * Author:   王震
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 这里注意 因为 是正向排序，如果要去第 k 大的元素 那么应该是 nums.lenght -k
        return quickSelect(nums,0,nums.length-1,nums.length-k);
    }

    public int quickSelect(int []arr,int left, int right,int k){
        int partition = partition(arr,left,right);
        if(partition==k){
            return arr[k];
        }else{
            if(partition<k){
                return quickSelect(arr,partition+1,right,k);
            }else {
                return quickSelect(arr,left,partition-1,k);
            }
        }
    }

    public int partition(int []arr,int left,int right){
        int random = new Random().nextInt(right-left+1)+left;
        swap(arr,left,random);
        int part = left;
        int index = left+1;
        for (int i = index;i<=right;i++){
            if(arr[i]<arr[part]){
                swap(arr,index,i);
                index++;
            }
        }
        swap(arr,part,index-1);
        return index-1;

    }

    public void swap(int []arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
