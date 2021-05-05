package com.douma._6_day._29;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _29_divide_two_integers {
    public int divide(int dividend, int divisor) {
        // -2147483648 ÷ (-1) = 2147483648
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;
        // 计算结果的符号
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        int a = Math.abs(dividend), b = Math.abs(divisor);
        int res = 0;

        for (int i = 31; i >= 0; i--) {
            if ((a >>> i) >= b) {
                a = a - (b << i);
                res += 1 << i;
            }
        }

        // 当 res == 1 << 31 的时候， -res 也等于 1 << 31
        return sign * res;
    }

    // 时间复杂度：O(logn^2)
    public int divide2(int dividend, int divisor) {
        // -2147483648 ÷ (-1) = 2147483648
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;
        // 计算结果的符号
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        long a = Math.abs((long)dividend), b = Math.abs((long)divisor);
        int res = 0;
        while (a - b >= 0) {
            long tmp = b;
            int m = 1;
            while (tmp << 1 <= a) {
                tmp <<= 1;
                m <<= 1;
            }
            a = a - tmp;
            res += m;
        }
        return sign * res;
    }

    // -2147483648
    // 4   超时
    // O(n)
    public int divide1(int dividend, int divisor) {
        // -2147483648 ÷ (-1) = 2147483648
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;
        // 计算结果的符号
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        int a = Math.abs(dividend), b = Math.abs(divisor);
        int res = 0;
        while (a - b >= 0) {
            res++;
            a -= b;
        }
        return sign * res;
    }

    public static void main(String[] args) {
        int a = -2147483648;
        // 最小值 -2147483648 的绝对值还是 -2147483648
        System.out.println(Math.abs(a));

        System.out.println(a - 4);

        System.out.println(new _29_divide_two_integers().divide(-2147483648, -2147483648));
    }
}
