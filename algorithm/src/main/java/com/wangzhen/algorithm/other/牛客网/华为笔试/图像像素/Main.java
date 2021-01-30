package com.wangzhen.algorithm.other.牛客网.华为笔试.图像像素;

/**
 * Description:
 * Datetime:    2020/11/7   8:24 下午
 * Author:   王震
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int [][]arr = new int[M][N];
        ArrayList<int []> list = new ArrayList<>();
        int sum = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                arr[i][j] = sc.nextInt();
                int [] nums= new int[2];
                if(arr[i][j]==5){
                    nums[0]=i;
                    nums[1]=j;
                }
                list.add(nums);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int []num = list.get(i);
            int n1 = num[0];
            int n2 = num[1];
            boolean flag =false;
            for (int j = i+1; j < list.size(); j++) {
                int []num2 = list.get(j);
                int m1 = num2[0];
                int m2 = num2[1];
                int dist=  Math.abs(m1-n1)+Math.abs(m2-n2);
                if(dist<8){
                    flag=true;
                    break;
                }

            }
            if(flag=false){
                sum++;
            }

        }
        System.out.println(sum);
    }



}