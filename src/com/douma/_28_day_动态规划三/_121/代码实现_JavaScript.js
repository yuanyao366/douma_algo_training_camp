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
        // 在这里有不少同学会认为下面的一行代码等价于：dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        // 注意：不能直接这样将三维压成二维，如果是三维的话应该是这样的：
        // dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
        // 因为 dp[i - 1][0][0] = 0，所以代码变为：dp[i][1][1] = Math.max(dp[i - 1][1][1],  - prices[i]);
        // 这个时候才可以将三维压成二维，即变成下面的代码
        dp[i][1] = Math.max(dp[i - 1][1], -prices[i])
    }

    return dp[len - 1][0]
};

var maxProfit = function(prices) {
    const len = prices.length

    let profit0 = 0;
    let profit1 = -prices[0];

    // 状态转移
    for (let i = 0; i < len; i++) {
        profit0 = Math.max(profit0, profit1 + prices[i]);
        profit1 = Math.max(profit1, -prices[i]);
    }

    return profit0;
};