package com.wangzhen.algorithm.other.牛客网.hj9;

/**
 * Description:
 * Datetime:    2020/11/7   8:48 下午
 * Author:   王震
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashSet<Character> linkedHashSet = new LinkedHashSet<>();
        String num = scanner.next();
        char [] nums = num.toCharArray();
        for (int i = nums.length-1; i >=0; i--) {
            linkedHashSet.add(nums[i]);
        }
        String result="";
        for (Character character : linkedHashSet) {
            result = result+character;
        }
        System.out.println(result);;

    }
}
