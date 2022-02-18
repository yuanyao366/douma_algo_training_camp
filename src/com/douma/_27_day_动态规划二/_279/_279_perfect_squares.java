package com.douma._27_day_动态规划二._279;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _279_perfect_squares {
    /* 279. 完全平方数
    给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
    你需要让组成和的完全平方数的个数最少。

    给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

    完全平方数 是一个整数，其值等于另一个整数的平方；
    换句话说，其值等于一个整数自乘的积。
    例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

    示例 1：
    输入：n = 12
    输出：3
    解释：12 = 4 + 4 + 4

    示例 2：
    输入：n = 13
    输出：2
    解释：13 = 4 + 9
     
    提示：
    1 <= n <= 10^4

     */

    // 完全平方数最小为 1，最大为 sqrt(n)
    // 也就是我们要从 nums = [1, 2, ..., sqrt(n)] 数组里选出几个数，
    // 令其平方和为 n(背包容量)。求最少的完全平方数
    public int numSquares(int n) {
        // dp[i] 表示和为 i 的 nums 组合中完全平方数最少个数
        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int num = 1; num <= Math.sqrt(n); num++) {
            for (int c = num * num; c <= n; c++) {
                dp[c] = Math.min(dp[c], dp[c - num * num] + 1);
            }
        }

        return dp[n];
    }
}
