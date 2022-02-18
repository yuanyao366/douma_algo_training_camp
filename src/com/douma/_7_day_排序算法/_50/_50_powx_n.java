package com.douma._7_day_排序算法._50;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _50_powx_n {
    // 快速幂 + 迭代
    // 时间：O(32)
    // 空间：O(1)
    public double myPow(double x, int n) {
        // bug 修复：使用 long 来存储 n，因为 -n 可能会越界
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        double res = 1;
        while (b != 0) {
            if ((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }
    // 快速幂 + 递归
    // 时间：O(logn)
    // 空间：O(logn)
    public double myPow2(double x, int n) {
        // bug 修复：使用 long 来存储 n，因为 -n 可能会越界
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        return quickPow(x, b);
    }

    private double quickPow(double x, long n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;

        long mid = n / 2;
        double y = quickPow(x, mid);
        return n % 2 == 0 ? y * y : x * y * y;
    }

    // O(n) --> 超时
    public double myPow1(double x, int n) {
        // bug 修复：使用 long 来存储 n，因为 -n 可能会越界
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        double res = 1;
        for (int i = 0; i < b; i++) {
            res *= x;
        }
        return res;
    }
}
