/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit1 = function(prices) {
    const len = prices.length
    // 状态定义
    // dp[i][j] 表示第 i 天处于状态 j 获取到的最大利益
    // 其中 j 可以取值为： 0 表示不持股；1 表示持股
    const dp = new Array(len).fill(0).map(() => new Array(2).fill(0))
    dp[0][0] = 0
    dp[0][1] = -prices[0]

    for (let i = 1; i < len; i++) {
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
        dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i])
    }

    return dp[len - 1][0]
};

var maxProfit = function(prices) {
    const len = prices.length

    let prevProfit0 = 0
    let profit0 = 0
    let profit1 = -prices[0]

    // 状态转移
    for (let i = 0; i < len; i++) {
        const nextProfit0 = Math.max(profit0, profit1 + prices[i])
        const nextProfit1 = Math.max(profit1, prevProfit0 - prices[i])
        prevProfit0 = profit0
        profit0 = nextProfit0
        profit1 = nextProfit1
    }

    return profit0
};