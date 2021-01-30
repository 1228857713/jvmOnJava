//package com.wangzhen.algorithm.other.牛客网.华为笔试.硬盘容量排序;
//
///**
// * Description:
// * Datetime:    2020/11/7   8:24 下午
// * Author:   王震
// */
//
//import java.util.Scanner;
//import java.util.TreeMap;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = Integer.parseInt(scanner.nextLine());
//        String [] strs = new String[n];
//        for (int i = 0; i < n; i++) {
//            strs[i]= scanner.nextLine();
//        }
//        TreeMap<Integer,String> treeMap = new TreeMap<>();
//        for (String str : strs) {
//            int sum =0;
//            String temp = str;
//            while (str!=null){
//                int M = str.indexOf("M")==-1 ? Integer.MAX_VALUE:str.indexOf("M");
//                int G = str.indexOf("G")==-1 ? Integer.MAX_VALUE:str.indexOf("G");
//                int T = str.indexOf("T")==-1 ? Integer.MAX_VALUE:str.indexOf("T");
//                int min = min(M,G,T);
//                char c = str.charAt(min);
//                int num = Integer.parseInt(str.substring(0,min));
//                if(c=='M'){
//                    sum +=num;
//                }else if(c=='G'){
//                    sum +=num*1024;
//                }else if(c=='T'){
//                    sum +=num*1024*1024;
//                }
//                if(min== str.length()-1){
//                    break;
//                }else {
//                    str = str.substring(min+1,str.length());
//                }
//
//            }
//            treeMap.put(sum,temp);
//        }
//        for (Integer integer : treeMap.keySet()) {
//            System.out.println(treeMap.get(integer));
//        }
//
//    }
//
//    public static int min(int m,int g,int t){
//        return Math.min(m,Math.min(g,t));
//    }
//
//    public static void main2(String[] args) {
//
////        String s= "3M12G9M";
////       // System.out.println(s.charAt(1));
////        System.out.println(s.substring(s.length()-1,s.length()));
////       // System.out.println(s.indexOf('G'));
//        TreeMap<Integer,String> treeMap = new TreeMap<>();
//        treeMap.put(10,"5");
//        treeMap.put(10,"20");
//        for (Integer integer : treeMap.keySet()) {
//            System.out.println(treeMap.get(integer));
//        }
//
//    }
//}
