package com.douma._4_day_字符串._58;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _58_length_of_last_word {
    // 从右往左遍历
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') end--;
        if (end < 0) return 0;

        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') start--;

        return end - start;
    }

    public int lengthOfLastWord1(String s) {
        int ans = 0;
        int start = 0, end = 0;
        while (end < s.length()) {
            if (s.charAt(start) == ' ') {
                start++;
                end++;
            } else {
                while (end < s.length() && s.charAt(end) != ' ') end++;
                ans = end - start;
                while (end < s.length() && s.charAt(end) == ' ') end++;
                if (end < s.length() && s.charAt(end) != ' ') {
                    start = end;
                }
            }
        }
        return ans;
    }
}
