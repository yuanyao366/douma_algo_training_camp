package com.douma._6_day_位运算._231;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _231_power_of_two {
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        // 当 n = -2147483648 的时候，-n 就会溢出
        long x = n;
        return (x & -x) == x;
    }

    public boolean isPowerOfTwo2(int n) {
        if (n == 0) return false;
        // 当 n = -2147483648 的时候，n - 1 就会溢出
        long x = n;
        return (x & (x - 1)) == 0;
    }
    public boolean isPowerOfTwo1(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n = n / 2;
        return n == 1;
    }
}
