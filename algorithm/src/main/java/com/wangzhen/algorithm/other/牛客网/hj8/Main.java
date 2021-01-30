package com.wangzhen.algorithm.other.牛客网.hj8;

/**
 * Description:
 * Datetime:    2020/11/7   8:42 下午
 * Author:   王震
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        TreeMap<Integer,Integer> map = new TreeMap();
        for(int i =0;i<num;i++){

            int s1 = scanner.nextInt();
            int s2 = scanner.nextInt();
            if(map.containsKey(s1)){
                int value = map.get(s1);
                map.put(s1,value+s2);
            }else{
                map.put(s1,s2);
            }
        }
        for(Integer i : map.keySet()){
            System.out.println(i+" "+map.get(i));
        }
    }
}