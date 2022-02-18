package com.douma._4_day_字符串._459;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _459_repeated_substring_pattern {

    // 字符串匹配法
    public boolean repeatedSubstringPattern(String s) {
        /*
        字符串 s 拼接的目的：可以在 s + s 中找到 s 的所有旋转后的字符串
        拼接后的字符串的头部是旋转了一圈的字符串，而尾部是没有旋转的字符串，所以需要去掉头部和尾部

        从 1 开始匹配的作用就是去掉头部

        不等于 s.length 的作用就是去掉尾部
         */
        return (s + s).indexOf(s, 1) != s.length();
    }

    // 旋转数组
    public boolean repeatedSubstringPattern2(String s) {
        for (int len = 1; len * 2 <= s.length(); len++) {
            String str = rotate(s.toCharArray(), len);
            if (s.equals(str)) return true;
        }
        return false;
    }

    public String rotate(char[] chars, int k) {
        int n = chars.length;
        k = k % n;
        reverse(chars, 0, n - 1);
        reverse(chars, 0, k - 1);
        reverse(chars, k, n - 1);
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }

    // 双指针模拟
    public boolean repeatedSubstringPattern1(String s) {
        int n = s.length();
        for (int len = 1; len * 2 <= n; len++) {
            if (n % len == 0) {
                boolean matched = true;
                int i = 0;
                for (int j = len; j < n; j++, i++) { // bug 修复：i 也需要加加
                    if (s.charAt(i) != s.charAt(j)) {
                        matched = false;
                        break;
                    }
                }
                if (matched) return true;
            }
        }
        return false;
    }
}
