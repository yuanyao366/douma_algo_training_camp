package com.douma._4_day_字符串._8;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class Test {
    public static void main(String[] args) {
        String str = "-213";
        int i = 0;
        int sign = 1;
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            sign = str.charAt(i) == '-' ? -1 : 1;
            i++;
        }
        int base = 0;
        while (i < str.length()) {
            base = base * 10 + (str.charAt(i) - '0');
            i++;
        }

        System.out.println(sign * base);
    }
}
