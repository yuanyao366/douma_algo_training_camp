package com.douma._4_day_字符串._557;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _557_reverse_words_in_a_string_iii {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        int left = 0;
        while (left < n) {
            if (chars[left] != ' ') {
                int right = left;
                while (right + 1 < n && chars[right + 1] != ' ') right++;
                reverseWord(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }

    private void reverseWord(char[] cArr, int start, int end) {
        char temp;
        while (start < end) {
            temp = cArr[start];
            cArr[start++] = cArr[end];
            cArr[end--] = temp;
        }
    }
}
