package com.wangzhen.algorithm.sort.ans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SortBaic {
    static Logger logger = LoggerFactory.getLogger(SortBaic.class);
    public abstract  Comparable[]  sort(Comparable[] a);

    // v 是否小于w
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    public static void swap(Comparable []a,int n,int m){
        Comparable t =a[n];
        a[n] = a[m];
        a[m] = t;
    }


    public static void show(Comparable []a){

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<a.length;i++){
            stringBuilder.append(","+a[i]);
           //logger.debug(a[i].toString());
        }
        logger.debug(stringBuilder.toString());
    }
    public static boolean isSorted(Comparable []a){
        for (int i=1;i<a.length;i++){
            if(less(a[i],a[i-1]))
                return false;
        }
        return true;
    }


    public void testSort(){
        Integer []array = {9,5,2,4,6,1,-1,6,0,33,43,22};
        Comparable [] result = sort(array);
        show(result);
    }


    public static void main(String[] args) {
        System.out.println(less(1, 2));
    }


}
