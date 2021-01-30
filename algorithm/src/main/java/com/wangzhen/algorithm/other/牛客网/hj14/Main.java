package com.wangzhen.algorithm.other.牛客网.hj14;

/**
 * Description:
 * Datetime:    2020/11/7   8:24 下午
 * Author:   王震
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] s = str.split(" ");
        String result=s[s.length-1];
        for (int i = s.length-2; i >=0; i--) {
            result = result+" "+s[i];
        }
        System.out.println(result);
    }
}
