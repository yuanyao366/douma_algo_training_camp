package com.douma._6_day_位运算._29;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _29_divide_two_integers {
    // 最初的想法
    public int divide1(int a, int b) {
        // 32 位最大值：2^31 - 1 = 2147483647
        // 32 位最小值：-2^31 = -2147483648
        // -2147483648 / (-1) = 2147483648 > 2147483647 越界了
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        // Math.abs(-2147483648) = -2147483648
        // 环境只支持存储 32 位整数
        long la = Math.abs((long)a);
        long lb = Math.abs((long)b);
        int res = 0;
        while (la >= lb) {
            la -= lb;
            res++;
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }

    // 因为将 -2147483648 转成正数会越界，但是将 2147483647 转成负数，则不会
    // 所以，我们将 a 和 b 都转成负数
    // 时间复杂度：O(n)，n 是最大值 2147483647 --> 10^10
    public int divide(int a, int b) {
        // 32 位最大值：2^31 - 1 = 2147483647
        // 32 位最小值：-2^31 = -2147483648
        // -2147483648 / (-1) = 2147483648 > 2147483647 越界了
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        // 环境只支持存储 32 位整数
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;
        while (a <= b) {
            a -= b;
            res++;
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }

    // 时间复杂度：O(logn * logn)，n 是最大值 2147483647 --> 10^10
    public int divide3(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;
        while (a <= b) {
            int value = b;
            int k = 1;
            // 0xc0000000 是十进制 -2^30 的十六进制的表示
            // 判断 value >= 0xc0000000 的原因：保证 value + value 不会溢出
            // 可以这样判断的原因是：0xc0000000 是最小值 -2^31 的一半，
            // 而 a 的值不可能比 -2^31 还要小，所以 value 不可能比 0xc0000000 小
            // -2^31 / 2 = -2^30
            while (value >= 0xc0000000 && a <= value + value) {
                value += value;
                // 代码优化：如果 k 已经大于最大值的一半的话，那么直接返回最小值
                // 因为这个时候 k += k 的话肯定会大于等于 2147483648 ，这个超过了题目给的范围
                if (k > Integer.MAX_VALUE / 2) {
                    return Integer.MIN_VALUE;
                }
                k += k;
            }
            a -= value;
            res += k;
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }

    // 时间复杂度：O(1)
    public int divide4(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            // 首先，右移的话，再怎么着也不会越界
            // 其次，无符号右移的目的是：将 -2147483648 看成 2147483648

            // 注意，这里不能是 (a >>> i) >= b 而应该是 (a >>> i) - b >= 0
            // 这个也是为了避免 b = -2147483648，如果 b = -2147483648
            // 那么 (a >>> i) >= b 永远为 true，但是 (a >>> i) - b >= 0 为 false
            if ((a >>> i) - b >= 0) { // a >= (b << i)
                a -= (b << i);
                // 代码优化：这里控制 res 大于等于 INT_MAX
                if (res > Integer.MAX_VALUE - (1 << i)) {
                    return Integer.MIN_VALUE;
                }
                res += (1 << i);
            }
        }
        // bug 修复：因为不能使用乘号，所以将乘号换成三目运算符
        return sign == 1 ? res : -res;
    }
}
