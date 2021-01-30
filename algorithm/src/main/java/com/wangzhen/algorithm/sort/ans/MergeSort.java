package com.wangzhen.algorithm.sort.ans;

import org.junit.Test;

import java.util.Arrays;

public class MergeSort extends SortBaic{
    @Override
    public Comparable[] sort(Comparable[] a) {
        if(a.length<2){
            return a;
        }
        //得到归并排序的中间值
        int middle = a.length >> 1;
        Comparable [] left = Arrays.copyOfRange(a,0,middle);
        Comparable [] right = Arrays.copyOfRange(a,middle,a.length);
        return  merge(sort(left),sort(right));

    }


    public Comparable[] merge(Comparable []left,Comparable []right){
        Comparable [] result = new Comparable[left.length+right.length];
        int i =0 ;
        while (left.length>0 && right.length>0){
            // 如果left[0] 小于 right[0],小的排前面，大的排在后面
            if(less(left[0],right[0])){
                 result [i++] =  left[0];
                 left = Arrays.copyOfRange(left,1,left.length);
            }else {
                result [i++] =  right[0];
                right = Arrays.copyOfRange(right,1,right.length);
            }

        }

        while (left.length>0){
            result[i++] = left[0];
            left = Arrays.copyOfRange(left,1,left.length);
        }

        while (right.length>0){
            result[i++] = right[0];
            right = Arrays.copyOfRange(right,1,right.length);
        }
        return result;
    }

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }
}
