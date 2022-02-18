package com.douma.笔试代码.zijie._20211010;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _2_CharsSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        // 1. 将字母和数字分开
        List<Character> chars = new ArrayList<>();
        List<Character> nums = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (isChar(c)) {
                chars.add(c);
            } else if (isDigit(c)) {
                nums.add(c);
            }
        }

        // 2. 对字母升序排列
        Collections.sort(chars);
        // 对数字降序排列
        Collections.sort(nums, (n1, n2) -> n2 - n1);

        // 3. 根据排序后的字母以及数字，再根据原先字母和数字的位置
        // 拼接有序的字母和数字
        int charIndex = 0, numsIndex = 0;
        for (char c : s.toCharArray()) {
            if (isChar(c)) {
                System.out.print(chars.get(charIndex));
                charIndex++;
            } else if (isDigit(c)) {
                System.out.print(nums.get(numsIndex));
                numsIndex++;
            } else {
                System.out.print("?");
            }
        }
    }

    private static boolean isChar(char c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    private static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
}
