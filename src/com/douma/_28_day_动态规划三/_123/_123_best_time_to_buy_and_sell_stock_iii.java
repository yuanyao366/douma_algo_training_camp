package com.douma._28_day_动态规划三._123;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _123_best_time_to_buy_and_sell_stock_iii {
    /* 123. 买卖股票的最佳时机 III
    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1:
    输入：prices = [3,3,5,0,0,3,1,4]
    输出：6
    解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
         随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

    示例 2：
    输入：prices = [1,2,3,4,5]
    输出：4
    解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
         注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
         因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

    示例 3：
    输入：prices = [7,6,4,3,1]
    输出：0
    解释：在这个情况下, 没有交易完成, 所以最大利润为 0。

    示例 4：
    输入：prices = [1]
    输出：0
     
    提示：
    1 <= prices.length <= 10^5
    0 <= prices[i] <= 10^5

     */

    /*
    dp[i][0][0] = 0
    dp[i][1][0] = max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i])
    dp[i][1][1] = max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i])
    dp[i][2][0] = max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i])
    dp[i][2][1] = max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i])
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[][][] dp = new int[n][3][2];

        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }

        return dp[n - 1][2][0];
    }

    // 状态压缩
    public int maxProfit2(int[] prices) {
        int n = prices.length;

        int profit10 = 0;
        int profit11 = -prices[0];
        int profit20 = 0;
        int profit21 = -prices[0];

        for (int i = 1; i < n; i++) {
            profit10 = Math.max(profit10, profit11 + prices[i]);
            profit11 = Math.max(profit11, -prices[i]);
            profit20 = Math.max(profit20, profit21 + prices[i]);
            profit21 = Math.max(profit21, profit10 - prices[i]);
        }

        return profit20;
    }
}
