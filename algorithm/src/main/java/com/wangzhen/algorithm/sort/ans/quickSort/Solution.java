package com.wangzhen.algorithm.sort.ans.quickSort;

import org.junit.Test;

/**
 * Description: 这次，今天一定要把快速排序搞定
 * Datetime:    2020/11/4   9:32 下午
 * Author:   王震
 */
public class Solution {

    @Test
    public void test(){
        int []array = {-74,48,-20,2,10,-84,-5,-9,11,-24,-91,2,-71,64,63,80,28,-30,-58,-11,-44,-87,-22,54,-74,-10,-55,-28,-46,29,10,50,-72,34,26,25,8,51,13,30,35,-8,50,65,-6,16,-2,21,-78,35,-13,14,23,-3,26,-90,86,25,-56,91,-13,92,-25,37,57,-20,-69,98,95,45,47,29,86,-28,73,-44,-46,65,-84,-96,-24,-12,72,-68,93,57,92,52,-45,-2,85,-63,56,55,12,-85,77,-39};
//        int [] array ={3,4,5,1,6,7,8};
        int []sortArray = sortArray(array);
        for (int i = 0; i < sortArray.length; i++) {
            System.out.println(sortArray[i]);
        }

    }

    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public  void quickSort(int []arr,int left,int right){
        if(left<right){
            int part = partition(arr,left,right);
            quickSort(arr,left,part -1);
            quickSort(arr,part+1,right);
        }
    }

    // 找到一个part 的值让part 右边的数据都比part大
    // 让part 左边的数据都比part小
    // 从右边向左找一个比part 大的值，从左边向右边找一个比part 大的值
    public  int partition(int []arr,int left,int right){
        // 选取左边的数作为基准值
        int part = arr[left];
        int temp = left;
        // 当left 和right 相遇就可直接退出
        while (left<right){
            while (left<right&&arr[right]>=part){
                right --;
            }

            //arr[left] = arr[right];
            while (left<right&&arr[left]<=part){
                left++;
            }
            //arr[right] = arr[left];
            swap(arr,left,right);
        }
       // arr[left] = part;
        swap(arr,temp,left);
        return left;
    }

    public void  swap(int []a ,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
