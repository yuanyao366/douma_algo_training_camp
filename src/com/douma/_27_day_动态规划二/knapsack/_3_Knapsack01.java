package com.douma._27_day_动态规划二.knapsack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _3_Knapsack01 {

    public int knapsack01(int[] w, int[] v, int C) {
        // 1. 状态定义：dp[i][c] 表示从 [0...i] 中选取物品放入容量为 c 的背包中的最大价值
        int[][] dp = new int[w.length][C + 1];

        // 2. 状态初始化
        // 考虑将第 0 号物品放入背包中
        for (int c = 0; c <= C; c++) {
            dp[0][c] = (c >= w[0] ? v[0] : 0);
        }

        // 3. 状态转移
        for (int i = 1; i < w.length; i++) {
            for (int c = 0; c <= C; c++) {
                if (c < w[i]) {
                    dp[i][c] = dp[i - 1][c];
                } else {
                    dp[i][c] = Math.max(dp[i - 1][c], v[i] + dp[i - 1][c - w[i]]);
                }
            }
        }

        return dp[w.length - 1][C];
    }


    public static void main(String[] args) {
        _3_Knapsack01 k = new _3_Knapsack01();
        int w[] = {3, 4, 5, 2};
        int v[] = {15, 10, 12, 8};

        System.out.println(k.knapsack01(w, v, 10));
    }
}
