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
            // bug 修复：如果使用 (a >>> i) >= b 的话，当 b = -2147483648 会出错
            // 因为 Math.abs(-2147483648) = -2147483648
            // 如果 (a >>> i) >= -2147483648，这个永远返回 true
            // 而 (a >>> i) - (-2147483648) >= 0 返回的是 false，这个是我们需要的，
            // 因为任何整形都是小于 2147483648 的，这个我们需要将 -2147483648 看成 2147483648
            // 任何整数减去 -2147483648 都是等于 -2147483648，也就是小于零，所以可以认为任何数都比 -2147483648 小
            // 从何可以看成任何整型小于 2147483648，这里有点绕，可以仔细揣摩下
            if ((a >>> i) - b >= 0) {
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

        System.out.println(new _29_divide_two_integers().divide(-1010369383, -2147483648));
    }
}
