package com.douma._4_day_字符串._8;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _8_string_to_integer_atoi {
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();

        int i = 0;
        // 1. 丢弃前导空格
        while (i < s.length() && chars[i] == ' ') i++;
        if (i == s.length()) return 0;

        // 2. 检查 + 和 - 是否存在
        int sign = 1;
        if (chars[i] == '-' || chars[i] == '+') {
            sign = chars[i] == '-' ? -1 : 1;
            i++;
        }

        // 3. 计算结果，并且检查是否溢出
        int base = 0;
        while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
            // 检查
            // 2147483647    -2147483648
            if (base > Integer.MAX_VALUE / 10 ||
                    (base == Integer.MAX_VALUE / 10 && chars[i] - '0' > 7)) {
                if (sign > 0) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }
            base = base * 10 + (chars[i] - '0');
            i++;
        }

        return sign * base;
    }
}
