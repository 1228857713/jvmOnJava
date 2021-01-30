package com.wangzhen.algorithm.other.牛客网.华为笔试.硬盘容量排序;

/**
 * Description: 对输入的硬盘容量进行排序 ：
 *  5
 * 1024M
 * 3T
 * 1G
 * 1G1024M4T
 * 1M2024M
 *
 * Datetime:    2020/11/7   8:24 下午
 * Author:   王震
 */

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String [] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i]= scanner.nextLine();
        }
       // TreeMap<Integer,String> treeMap = new TreeMap<>();
        Container [] containers = new Container[n];
        int i =0;
        for (String str : strs) {
            int sum =0;
            String temp = str;
            while (str!=null){
                int M = str.indexOf("M")==-1 ? Integer.MAX_VALUE:str.indexOf("M");
                int G = str.indexOf("G")==-1 ? Integer.MAX_VALUE:str.indexOf("G");
                int T = str.indexOf("T")==-1 ? Integer.MAX_VALUE:str.indexOf("T");
                int min = min(M,G,T);
                char c = str.charAt(min);
                int num = Integer.parseInt(str.substring(0,min));
                if(c=='M'){
                    sum +=num;
                }else if(c=='G'){
                    sum +=num*1024;
                }else if(c=='T'){
                    sum +=num*1024*1024;
                }
                if(min== str.length()-1){
                    break;
                }else {
                    str = str.substring(min+1,str.length());
                }

            }
            Container container = new Container(temp,sum);
            containers[i]=container;
            i++;

        }

        Arrays.sort(containers);
        for (int j = 0; j < containers.length; j++) {
            System.out.println(containers[j].getValue());
        }


    }

    public static int min(int m,int g,int t){
        return Math.min(m,Math.min(g,t));
    }



    public static void main2(String[] args) {

//        String s= "3M12G9M";
//       // System.out.println(s.charAt(1));
//        System.out.println(s.substring(s.length()-1,s.length()));
//       // System.out.println(s.indexOf('G'));
        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(10,"5");
        treeMap.put(10,"20");
        for (Integer integer : treeMap.keySet()) {
            System.out.println(treeMap.get(integer));
        }

    }
}

class Container implements Comparable{
    int size;
    String value;

    public int getSize() {
        return size;
    }

    public String getValue() {
        return value;
    }

    public Container(String value, int size) {
        this.size = size;
        this.value = value;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.size>=((Container)o).size ? 1:-1;
    }
}