package com.douma._28_day_动态规划三._122;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _122_best_time_to_buy_and_sell_stock_ii {
    /* 122. 买卖股票的最佳时机 II
    给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。

    设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1:
    输入: prices = [7,1,5,3,6,4]
    输出: 7
    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

    示例 2:
    输入: prices = [1,2,3,4,5]
    输出: 4
    解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

    示例 3:
    输入: prices = [7,6,4,3,1]
    输出: 0
    解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     
    提示：
    1 <= prices.length <= 3 * 10^4
    0 <= prices[i] <= 10^4
     */

    /*
    dp[i][0][0] = 0
    dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
    dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     */
    public int maxProfit1(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 状态压缩
    public int maxProfit2(int[] prices) {
        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            profit0 = Math.max(profit0, profit1 + prices[i]);
            profit1 = Math.max(profit1, profit0 - prices[i]);
        }

        return profit0;
    }

    // 贪心算法
    public int maxProfit3(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}
