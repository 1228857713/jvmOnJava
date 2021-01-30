package com.wangzhen.algorithm.other.牛客网.hj10;

/**
 * Description:
 * Datetime:    2020/11/7   8:24 下午
 * Author:   王震
 */

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        HashSet<Character> set = new HashSet<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
        }
        System.out.println(set.size());

    }
}
