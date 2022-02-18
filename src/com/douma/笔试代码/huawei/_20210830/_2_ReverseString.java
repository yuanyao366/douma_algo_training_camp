package com.douma.笔试代码.huawei._20210830;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 * 官方微信：bigdatatang01
 * 官方公众号：抖码课堂
 * @作者 : 老汤
 */
public class _2_ReverseString {

    // 时间复杂度为 O(n)
    // 空间复杂度为 O(1)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        /*
        输入：s = "  Bob&123---Loves -Alice--abc   "
        第一步. 将空格替换连续 2 个以及更多的 -，得到：s = "  Bob&123 Loves -Alice abc   "
        第二步. 将非法字符当成空格，并且去掉多余的空格，得到：s = "Bob 123 Loves -Alice abc"
        第三步. 反转字符串，得到："cba ecilA- sevoL 321 boB"
        第四步. 反转每个单词，得到结果："abc -Alice Loves 123 Bob"
         */
        // 1. 将空格替换连续 2 个以及更多的 -
        s = trimBar(s.toCharArray(), s.length());

        // 2. 将非法字符当成空格，并且去掉多余的空格
        s = trimSpaces(s.toCharArray(), s.length());

        // 3. 反转字符串
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);

        // 4. 反转每个单词
        s = reverseEachWord(chars);

        System.out.println(s);
    }

    // 使用空格替换连续 2 个以及更多的 -
    // 时间复杂度为 O(n)
    // 空间复杂度为 O(1)
    // 快慢指针
    private static String trimBar(char[] chars, int n) {
        int slow = 0, fast = 0;
        while (fast < n) {
            if (chars[fast] == '-') {
                int cnt = 0;
                while (fast < n && chars[fast] == '-') {
                    cnt++;
                    fast++;
                }
                if (cnt >= 2) {
                    chars[slow++] = ' ';
                } else {
                    chars[slow++] = chars[fast - 1];
                }
            } else {
                chars[slow++] = chars[fast++];
            }
        }
        return new String(chars).substring(0, slow);
    }

    // 时间复杂度为 O(n)
    // 空间复杂度为 O(1)
    // 快慢指针
    private static String trimSpaces(char[] chars, int n) {
        int slow = 0, fast = 0;

        while (fast < n) {
            while (fast < n && !isValidChar(chars[fast])) fast++;             // skip spaces
            while (fast < n && isValidChar(chars[fast])) chars[slow++] = chars[fast++]; // keep non spaces
            while (fast < n && !isValidChar(chars[fast])) fast++;             // skip spaces
            if (fast < n) chars[slow++] = ' ';                      // keep only one space
        }

        return new String(chars).substring(0, slow);
    }

    private static boolean isValidChar(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c >= '0' && c <= '9') ||
                c == '-';
    }

    // 反转字符串中的每个单词
    // 时间复杂度为 O(n)
    // 空间复杂度为 O(1)
    private static String reverseEachWord(char[] chars) {
        int n = chars.length;
        int left = 0;
        while (left < n) {
            if (chars[left] != ' ') {
                int right = left;
                while (right + 1 < n && chars[right + 1] != ' ') right++;
                reverse(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }

    // 反转字符串
    // 时间复杂度为 O(n)
    // 空间复杂度为 O(1)
    private static void reverse(char[] cArr, int start, int end) {
        char temp;
        while (start < end) {
            temp = cArr[start];
            cArr[start++] = cArr[end];
            cArr[end--] = temp;
        }
    }
}
