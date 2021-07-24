package com.douma._27_day.knapsack;

/*
    多重背包：
        有 n 种物品和一个容量为 C 的背包
        第 i 种物品的重量是 w[i]，价值是 v[i]，件数是 p[i]
        求将哪些物品装入背包可使得价值总和最大
 */
public class _10_KnapsackMultiple {

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
}
