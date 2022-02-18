package com.douma._25_day_贪心算法二._738;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _738_monotone_increasing_digits {
    /* 738. 单调递增的数字
    给定一个非负整数 N，找出小于或等于 N 的最大的整数，
    同时这个整数需要满足其各个位数上的数字是单调递增。

    （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

    示例 1:
    输入: N = 10
    输出: 9

    示例 2:
    输入: N = 1234
    输出: 1234

    示例 3:
    输入: N = 332
    输出: 299

    说明: N 是在 [0, 10^9] 范围内的一个整数。
     */

    public int monotoneIncreasingDigits(int n) {
        char[] strN = String.valueOf(n).toCharArray();

        // 1. 找到第一个递减的位
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) i++;

        if (i < strN.length) {
            // 不断将前一个数字 -1，直到前一个数字小于等于后一个数字
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i--;
            }
            // 将 i 后面的数字都设置为 9
            i++;
            while (i < strN.length) {
                strN[i++] = '9';
            }
            return Integer.parseInt(new String(strN));
        } else {
            return n;
        }
    }
}
