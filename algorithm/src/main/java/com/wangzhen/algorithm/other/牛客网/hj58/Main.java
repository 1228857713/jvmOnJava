package com.wangzhen.algorithm.other.牛客网.hj58;

/**
 * Description:
 * Datetime:    2020/11/7   8:24 下午
 * Author:   王震
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            // 注意 nextLine() 是得到当前的一行，以换行符结束。 而 next() 是获得一个字符，以空格结束
            String input = scanner.nextLine();
            String []inputs = input.split(" ");
            int n = Integer.parseInt(inputs[0]);
            int k = Integer.parseInt(inputs[1]);
            String array = scanner.nextLine();
            String []arrays = array.split(" ");
            Arrays.sort(arrays);
            StringBuilder ans = new StringBuilder();
            for(int i=0;i<k;i++){
                ans.append(arrays[i]).append(" ");

            }
            System.out.println(ans.toString().trim());

        }
        // return ;


    }
}
