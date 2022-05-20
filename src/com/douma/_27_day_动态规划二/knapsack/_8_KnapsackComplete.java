package com.douma._27_day_动态规划二.knapsack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _8_KnapsackComplete {

    public int knapsackComplete(int[] w, int[] v, int C) {
        // 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[C + 1];

        // 2. 状态初始化
        // 考虑将第 0 号物品放入背包中
        for (int c = 0; c <= C; c++) {
            dp[c] = (c / w[0]) * v[0];
        }

        // 3. 状态转移
        for (int i = 1; i < w.length; i++) {
            for (int c = 0; c <= C; c++) {
                int count = c / w[i];
                for (int k = 0; k <= count; k++) {
                    dp[c] = Math.max(dp[c], k * v[i] + dp[c - k * w[i]]);
                }
            }
        }

        return dp[C];
    }


    public static void main(String[] args) {
        _8_KnapsackComplete k = new _8_KnapsackComplete();
        int w[] = {3, 4, 5, 2};
        int v[] = {15, 10, 12, 8};

        System.out.println(k.knapsackComplete(w, v, 10));
    }
}
