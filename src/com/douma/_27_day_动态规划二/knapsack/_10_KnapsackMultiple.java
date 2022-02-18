package com.douma._27_day_动态规划二.knapsack;

/*
    多重背包：
        有 n 种物品和一个容量为 C 的背包
        第 i 种物品的重量是 w[i]，价值是 v[i]，件数是 p[i]
        求将哪些物品装入背包可使得价值总和最大
 */
public class _10_KnapsackMultiple {

    // bug 修复，这道题目的实现有 bug，请看下面的 knapsack1 方法
    public int knapsack(int[] w, int[] v, int[] p, int C) {
        int len = w.length;
        if (len == 0) {
            return 0;
        }
        // 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[C + 1];

        // 2. 状态初始化

        // 3. 状态转移
        // 时间复杂度：
        for (int i = 0; i < len; i++) {
            for (int c = 0; c <= C; c++) {
                int count = Math.min(c / w[i], p[i]);
                for (int k = 0; k <= count; k++) {
                    dp[c] = Math.max(dp[c], k * v[i] + dp[c - k * w[i]]);
                }
            }
        }

        // 4. 返回结果
        return dp[C];
    }

    // 修改后的多重背包的实现
    // 把多重背包问题，转化成 0-1 背包问题
    public int knapsack1(int[] w, int[] v, int[] p, int C) {
        int len = w.length;
        if (len == 0) {
            return 0;
        }

        // 比如，有 2 件价值为 5，重量为 2 的同一物品，
        // 我们就可以分为物品 a和物品 b，a 和 b 的价值都为 5，重量都为 2，
        // 但我们把它们视作不同的物品。
        int newN = 0;
        for (int i = 0; i < len; i++) {
            newN += p[i];
        }

        int[] newW = new int[newN];
        int[] newV = new int[newN];
        int index = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < p[i]; j++) {
                newW[index] = w[i];
                newV[index] = v[i];
                index++;
            }
        }

        // 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[C + 1];

        // 2. 状态初始化

        // 3. 状态转移
        for (int i = 0; i < newN; i++) {
            for (int c = C; c >= newW[i]; c--) {
                dp[c] = Math.max(dp[c], newV[i] + dp[c - newW[i]]);
            }
        }

        // 4. 返回结果
        return dp[C];
    }
}
