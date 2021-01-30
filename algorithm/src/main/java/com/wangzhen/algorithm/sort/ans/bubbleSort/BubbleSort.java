package com.wangzhen.algorithm.sort.ans.bubbleSort;

import com.wangzhen.algorithm.sort.ans.SortBaic;
import org.junit.Test;


/**
 * Description:   冒泡排序
 * Datetime:    2020/9/17   6:09 下午
 * Author:   王震
 */
public class BubbleSort extends SortBaic {

    @Override
    public Comparable[] sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j <a.length-i-1 ; j++) {
                if(a[j].compareTo(a[j+1])>0){
                    swap(a,j,j+1);
                }
            }
        }
        return a;
    }

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }
}
