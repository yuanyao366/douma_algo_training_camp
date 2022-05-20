package com.douma._27_day_动态规划二.knapsack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _9_KnapsackComplete {

    public int knapsackComplete(int[] w, int[] v, int C) {
        // 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[C + 1];

        // 2. 状态初始化

        // 3. 状态转移
        for (int i = 0; i < w.length; i++) {
            // 注意：这里和 01 背包还是有区别的
            // 01 背包在遍历容量的时候是从右往左遍历，而完全背包是从左往右遍历的
            for (int c = w[i]; c <= C; c++) {
                // 放 第一个物品产生的价值永远大于等于放 第 2、3、4、5.... 个
                // 如果放第一个物品产生的价值比不放这个物品产生的价值要小的话
                // 那么不放物品，产生的价值最大
                dp[c] = Math.max(dp[c], v[i] + dp[c - w[i]]);
            }
        }

        return dp[C];
    }


    public static void main(String[] args) {
        _9_KnapsackComplete k = new _9_KnapsackComplete();
        int w[] = {3, 4, 5, 2};
        int v[] = {15, 10, 12, 8};

        System.out.println(k.knapsackComplete(w, v, 10));
    }
}
