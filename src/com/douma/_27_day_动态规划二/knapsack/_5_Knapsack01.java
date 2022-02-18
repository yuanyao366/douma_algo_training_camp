package com.douma._27_day_动态规划二.knapsack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _5_Knapsack01 {

    public int knapsack01(int[] w, int[] v, int C) {
        // 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[C + 1];

        // 2. 状态初始化

        // 3. 状态转移
        for (int i = 0; i < w.length; i++) {
            for (int c = C; c >= w[i]; c--) {
                dp[c] = Math.max(dp[c], v[i] + dp[c - w[i]]);
            }
        }

        return dp[C];
    }


    public static void main(String[] args) {
        _5_Knapsack01 k = new _5_Knapsack01();
        int w[] = {3, 4, 5, 2};
        int v[] = {15, 10, 12, 8};

        System.out.println(k.knapsack01(w, v, 10));
    }
}
