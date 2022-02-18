package com.douma._4_day_字符串._28;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _28_implement_strstr {
    // 字符串匹配算法：暴力解法、RK 算法、BM 算法、KMP 算法
    // 详细参见：课程 A：应用篇 -> 字符串匹配
    // 暴力
    // 时间复杂度：O(m*n)
    // 空间复杂度：O(1)
    public int strStr1(String haystack, String needle) {
        int n = needle.length();
        if (n == 0) return 0;
        char first = needle.charAt(0);
        for (int i = 0; i < haystack.length() - n + 1; i++) {
            // 如果等于 needle 的第一个字符，再进行 n 个字符的比较
            if (haystack.charAt(i) == first
                    && haystack.substring(i, i + n).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // KMP
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(n)
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) return 0;
        if (m < n) return -1;

        // 根据模式串所有的前缀，计算得到 next 数组
        int[] next = getNext(needle.toCharArray());

        int j = 0;
        for (int i = 0; i < m; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                // 就不从头开始匹配了，直接跳到下一个最长匹配前缀字符串的结尾字符的下一个字符位置
                j = next[j - 1] + 1;
            }

            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }

            if (j == n) {
                return i - n + 1;
            }
        }

        return -1;
    }

    private int[] getNext(char[] needle) {
        int n = needle.length;
        if (n == 1) return new int[0];

        int[] next = new int[n - 1];

        next[0] = -1;

        for (int j = 1; j < n - 1; j++) {
            int pre = next[j - 1];
            while (pre != -1 && needle[pre + 1] != needle[j]) {
                // 因为前一个最长串的下一个字符不与最后一个相等，所以需要找前一个的次长串
                // 问题就变成了求 0 到 next(pre) 的最长串
                pre = next[pre];
            }
            if (needle[pre + 1] == needle[j]) {
                pre++;
            }
            next[j] = pre;
        }
        // 最值问题
        return next;
    }
}
