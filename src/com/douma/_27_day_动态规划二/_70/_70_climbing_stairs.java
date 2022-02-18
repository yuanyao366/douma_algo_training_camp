package com.douma._27_day_动态规划二._70;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _70_climbing_stairs {
    /* 70. 爬楼梯
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    注意：给定 n 是一个正整数。

    示例 1：
    输入： 2
    输出： 2
    解释： 有两种方法可以爬到楼顶。
    1.  1 阶 + 1 阶
    2.  2 阶

    示例 2：
    输入： 3
    输出： 3
    解释： 有三种方法可以爬到楼顶。
    1.  1 阶 + 1 阶 + 1 阶
    2.  1 阶 + 2 阶
    3.  2 阶 + 1 阶
     */

    // DFS + 记忆化技术
    public int climbStairs1(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        if (memo[n] != -1) return memo[n];

        int left = dfs(n - 1, memo);
        int right = dfs(n - 2, memo);

        memo[n] = left + right;
        return memo[n];
    }

    // 动态规划
    public int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        // 1. 状态 dp[i]：表示走到第 i 个台阶的方法数
        int[] dp = new int[n + 1];

        // 2. 状态初始化
        dp[1] = 1;
        dp[2] = 2;

        // 3. 状态转移
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 4. 返回结果
        return dp[n];
    }

    // 动态规划 + 状态空间压缩
    public int climbStairs3(int n) {
        // bug 修复：需要对 1 和 2 做特判，要不然当 n = 1 的时候，会返回错误结果
        if (n == 1) return 1;
        if (n == 2) return 2;

        int prev = 1;
        int curr = 2;

        for (int i = 3; i <= n; i++) {
            int tmp = curr + prev;
            prev = curr;
            curr = tmp;
        }

        return curr;
    }
}
