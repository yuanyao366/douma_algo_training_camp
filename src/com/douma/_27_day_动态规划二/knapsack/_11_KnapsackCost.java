package com.douma._27_day_动态规划二.knapsack;

/*
    二维费用背包问题：
        有 n 种物品，每种物品只有 1 件，选择一种物品必须付出两种代价
        第 i 种物品第一种代价值是 w[i]，第二种代价值是 g[i]，物品的价值是 v[i]
        对于每种代价都有一个可付出的最大值（两种背包容量）W 和 G

        问怎样选择物品可以得到最大的价值。
 */
public class _11_KnapsackCost {
    public int knapsack_1(int[] w, int[] g, int W, int G, int[] v) {
        // dp[i][j]：表示选择物品时付出两种代价分别为 i 和 j 时可获得的最大价值
        int[][] dp = new int[W + 1][G + 1];

        for (int i = 0; i < w.length; i++) {
            for (int j = W; j >= w[i]; j--) {
                for (int k = G; k >= g[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - w[i]][k - g[i]] + v[i]);
                }
            }
        }

        return dp[W][G];
    }
}
