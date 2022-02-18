package com.douma._28_day_动态规划三._309;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _309_best_time_to_buy_and_sell_stock_with_cooldown {
    /* 309. 最佳买卖股票时机含冷冻期
    给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

    设计一个算法计算出最大利润。
    在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
        1. 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
        2. 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。

    示例:
    输入: [1,2,3,0,2]
    输出: 3
    解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

     */

    public int maxProfit1(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 在有「冷却时间」的情况下，如果在第 i - 1 天卖出了股票，就不能在第 i 天买入股票。
            // 因此，如果要在第 i 天买入股票，那么在第 i - 1 天就不能卖出股票
            // 那么第 i - 1 天不买股票并且不持有股票获取最大的利润就
            // 等于第 i - 2 天不持有股票的最大利润 dp[i - 2][0]
            dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 状态压缩
    public int maxProfit2(int[] prices) {
        int prevProfit0 = 0;
        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int nextProfit0 = Math.max(profit0, profit1 + prices[i]);
            int nextProfit1 = Math.max(profit1, prevProfit0 - prices[i]);
            prevProfit0 = profit0;
            profit0 = nextProfit0;
            profit1 = nextProfit1;
        }

        return profit0;
    }
}
