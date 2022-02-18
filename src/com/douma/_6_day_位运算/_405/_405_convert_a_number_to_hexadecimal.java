package com.douma._6_day_位运算._405;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _405_convert_a_number_to_hexadecimal {
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] hexChars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        String res = "";
        while (num != 0) {
            int index = num & 15;
            res = hexChars[index] + res;
            num >>>= 4;
        }
        return res;
    }
}
