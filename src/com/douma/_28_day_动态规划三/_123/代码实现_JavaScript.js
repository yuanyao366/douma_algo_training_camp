/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit1 = function(prices) {
    const len = prices.length;

    // 1. 状态定义
    // dp[i][k][s] : 表示到下标为 i 的天数为止(从 0 开始)，
    // 发生了 k 次交易次数(从 1 开始)，第 0 次交易的话，利润肯定是 0
    // 状态为 s 的最大利润
    // s = 1 表示持股，s = 0 表示不持股
    const dp = new Array(len).fill(0).map(() => new Array(3).fill(0).map(() => new Array(2).fill(0)))

    // 2. 状态初始化
    // 对于第一天：
    dp[0][1][0] = 0
    dp[0][1][1] = -prices[0]
    dp[0][2][0] = 0
    dp[0][2][1] = -prices[0]

    // 3. 状态转移
    for (let i = 1; i < len; i++) {
        dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i])
        dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i])
        dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i])
        dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i])
    }

    return dp[len - 1][2][0]
};

var maxProfit = function(prices) {
    const len = prices.length;

    let profit10 = 0;
    let profit11 = -prices[0];
    let profit20 = 0;
    let profit21 = -prices[0];

    // 3. 状态转移
    for (let i = 1; i < len; i++) {
        profit10 = Math.max(profit10, profit11 + prices[i]);
        profit11 = Math.max(profit11, -prices[i]);
        profit20 = Math.max(profit20, profit21 + prices[i]);
        profit21 = Math.max(profit21, profit10 - prices[i]);
    }

    return profit20;
}