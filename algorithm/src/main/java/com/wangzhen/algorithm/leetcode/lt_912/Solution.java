package com.wangzhen.algorithm.leetcode.lt_912;


import java.util.Random;

/**
 * Description: 快速排序
 * Datetime:    2020/9/29   11:29 上午
 * Author:   王震
 */
class Solution {
    public static void main(String[] args) {
        //System.out.println(new Random().nextInt(4));
        int []array = {-74,48,-20,2,10,-84,-5,-9,11,-24,-91,2,-71,64,63,80,28,-30,-58,-11,-44,-87,-22,54,-74,-10,-55,-28,-46,29,10,50,-72,34,26,25,8,51,13,30,35,-8,50,65,-6,16,-2,21,-78,35,-13,14,23,-3,26,-90,86,25,-56,91,-13,92,-25,37,57,-20,-69,98,95,45,47,29,86,-28,73,-44,-46,65,-84,-96,-24,-12,72,-68,93,57,92,52,-45,-2,85,-63,56,55,12,-85,77,-39};
        Solution solution = new Solution();
        solution.sortArray(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }
    public void quickSort(int []arr,int left,int right){
        if (left<right){
            int part = partition(arr,left,right);
            quickSort(arr,left,part-1);
            quickSort(arr,part+1,right);
        }
    }

    public int partition(int []arr,int left,int right){
        int temp= new Random().nextInt(right-left+1)+left;
        swap(arr,left,temp);
        int part = left;
        int index = left+1;
        for (int i = index;i<= right;i++){
            if(arr[i]<arr[part]){
                swap(arr,index,i);
                index++;
            }
        }
        swap(arr,part,index-1);
        return  index-1;
    }

//    public int partition(int []arr,int left,int right){
//        int part = left;
//        int index = part+1;
//        for (int i = index; i<= right;i++){
//            if(arr[i]<arr[part]){
//                swap(arr,i,index);
//                index++;
//            }
//        }
//        swap(arr,part,index-1);
//        return index -1;
//    }
    public void swap(int []arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
