package com.douma._5_day_数学._7;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _7_reverse_integer {
    // 方法二：溢出之后判断
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            int newRes = res * 10 + pop;
            if ((newRes - pop) / 10 != res) return 0;
            res = newRes;
        }
        return res;
    }
    // 方法一：溢出之前判断
    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            // MAX_VALUE = 2^31 - 1 = 2147483647
            if (res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10 && pop > 7)) return 0; // bug 修复：这里是除以 10
            // MIN_VALUE = -2^31 = -2147483648
            if (res < Integer.MIN_VALUE / 10
                    || (res == Integer.MIN_VALUE  / 10 && pop < -8)) return 0;
            res = res * 10 + pop;
        }
        return res;
    }
    public int reverse1(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int left = 0, right = str.length() - 1;
        // "-123" -> "-321"
        if (chars[left] < '0' || chars[left] > '9') left++;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
        // 如果反转后，数据溢出的话
        long res = Long.parseLong(new String(chars));
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        return (int)res;
    }
}
