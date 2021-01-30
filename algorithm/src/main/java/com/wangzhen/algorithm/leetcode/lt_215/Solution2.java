package com.wangzhen.algorithm.leetcode.lt_215;

import com.wangzhen.algorithm.leetcode.lt_912.QuickSort;

import java.util.Random;

/**
 * Description: 基于快速排序来实现
 * desc :       不需要完全实现快速排序
 * Datetime:    2020/9/28   9:23 下午
 * Author:   王震
 */
class Solution2 {

    public static void main(String[] args) throws Exception {
        //System.out.println(new Random().nextInt(4));
        int []array = {-74,48,-20,2,10,-84,-5,-9,11,-24,-91,2,-71,64,63,80,28,-30,-58,-11,-44,-87,-22,54,-74,-10,-55,-28,-46,29,10,50,-72,34,26,25,8,51,13,30,35,-8,50,65,-6,16,-2,21,-78,35,-13,14,23,-3,26,-90,86,25,-56,91,-13,92,-25,37,57,-20,-69,98,95,45,47,29,86,-28,73,-44,-46,65,-84,-96,-24,-12,72,-68,93,57,92,52,-45,-2,85,-63,56,55,12,-85,77,-39};
        Solution2 solution = new Solution2();
        solution.quickSelect(array,0,array.length-1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public int findKthLargest(int[] nums, int k) {
        // 这里注意 因为 是正向排序，如果要去第 k 大的元素 那么应该是 nums.lenght -k
        quickSelect(nums,0,nums.length-1);
        return nums[nums.length-k];
    }

    public void quickSelect(int []arr,int left, int right){
        if(left<right){
            int part = partition(arr,left,right);
            quickSelect(arr,left,part-1);
            quickSelect(arr,part+1,right);
        }
    }

    public int partition(int []arr,int left,int right){
//        int random = new Random().nextInt(right-left+1)+left;
//        swap(arr,left,random);
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
