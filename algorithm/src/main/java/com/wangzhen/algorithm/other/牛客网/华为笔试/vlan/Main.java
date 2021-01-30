package com.wangzhen.algorithm.other.牛客网.华为笔试.vlan;

/**
 * Description:
 * Datetime:    2020/11/7   8:24 下午
 * Author:   王震
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            String []vlanPools = null;
            if(input.contains(",")){
                vlanPools = input.split(",");
            }else{
                vlanPools = new String[1];
                vlanPools[0]=input;
            }
            int vlan = scanner.nextInt();
            // 结果
            ArrayList<String> list = new ArrayList<>();
            TreeMap<Integer,String> treeMap = new TreeMap<>();
            for (int i = 0; i < vlanPools.length; i++) {
                // 有 -
                if(vlanPools[i].contains("-")){
                    String []ans = vlanPools[i].split("-");
                    int left = Integer.parseInt(ans[0]);
                    int right = Integer.parseInt(ans[1]);
                    if(vlan==left){
                        int temp = left+1;
                        if(temp == right){
                            // list.add(right+"");
                            treeMap.put(right,right+"");
                        }else{
                            String temp2 = temp+"-"+right;
                            //list.add(temp2);
                            treeMap.put(temp,temp2);
                        }
                    }else if(vlan == right){
                        int temp = right-1;
                        if(temp == left){
                            //  list.add(left+"");
                            treeMap.put(left,left+"");
                        }else {
                            String temp2=left+"-"+temp;
                            //list.add(temp2);
                            treeMap.put(left,temp2);
                        }
                    }else if(vlan>left&&vlan<right){
                        int temp1 = vlan-1;
                        int temp2 = vlan+1;
                        if(temp1==left&&temp2==right){
                            treeMap.put(left,left+"");
                            treeMap.put(right,right+"");
                        }else if(temp1 == left){
                            treeMap.put(left,left+"");
                            treeMap.put(temp2,temp2+"-"+right);
                        }else if(temp2==right){
                            treeMap.put(left,left+"-"+temp1);
                        }else {
                            treeMap.put(left,left+"-"+temp1);
                            treeMap.put(temp2,temp2+"-"+right);
                        }
                        // list.add(left+"-"+temp1);
                        // list.add(temp2+"-"+right);

                    }else{
                        treeMap.put(left,vlanPools[i]);
                    }
                }else{
                    int val = Integer.parseInt(vlanPools[i]);
                    if(val!=vlan){
                        //list.add(vlanPools[i]);
                        treeMap.put(val,vlanPools[i]);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            if(treeMap.size()==0){
                System.out.println();
            }else{
                for (Integer integer : treeMap.keySet()) {
                    sb.append(treeMap.get(integer)).append(",");
                }
                System.out.println(sb.toString().substring(0,sb.toString().length()-1));
            }


        }


    }



