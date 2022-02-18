package com.douma._28_day_动态规划三._121;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _121_best_time_to_buy_and_sell_stock {
    /* 121. 买卖股票的最佳时机

    给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
    设计一个算法来计算你所能获取的最大利润。
    返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

    示例 1：
    输入：[7,1,5,3,6,4]
    输出：5
    解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
         注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

    示例 2：
    输入：prices = [7,6,4,3,1]
    输出：0
    解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     
    提示：
    1 <= prices.length <= 10^5
    0 <= prices[i] <= 10^4
     */

    /*
    dp[i][0][0] = 0
    dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
    dp[i][1] = max(dp[i - 1][1], -prices[i])
     */
    public int maxProfit1(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 在这里有不少同学会认为下面的一行代码等价于：dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 注意：不能直接这样将三维压成二维，如果是三维的话应该是这样的：
            // dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
            // 因为 dp[i - 1][0][0] = 0，所以代码变为：dp[i][1][1] = Math.max(dp[i - 1][1][1],  - prices[i]);
            // 这个时候才可以将三维压成二维，即变成下面的代码
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // 状态压缩
    public int maxProfit2(int[] prices) {
        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            profit0 = Math.max(profit0, profit1 + prices[i]);
            profit1 = Math.max(profit1, -prices[i]);
        }

        return profit0;
    }
}
