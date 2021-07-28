/**
 * @param {number} k
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit1 = function(k, prices) {
    const len = prices.length;
    if (k == 0 || len < 2) return 0

    if ( k >= len / 2) {
        // 相当于
        // 使用贪心算法求解
        return maxProfitHelp(prices)
    }

    // 1. 状态定义
    // dp[i][k][s] : 表示到下标为 i 的天数为止(从 0 开始)，
    // 发生了 k 次交易次数(从 1 开始)，第 0 次交易的话，利润肯定是 0
    // 状态为 s 的最大利润
    // s = 1 表示持股，s = 0 表示不持股
    const dp = new Array(len).fill(0).map(() => new Array(k + 1).fill(0).map(() => new Array(2).fill(0)))

    // 2. 状态初始化，所有的状态初始化为 0
    // 对于第一天：
    for (let j = 1; j <= k; j++) {
        // 第一天不持股的利润
        dp[0][j][0] = 0
        // 第一天持股的利润
        dp[0][j][1] = -prices[0]
    }

    // 3. 状态转移
    for (let i = 1; i < len; i++) {
        for (let j = 1; j <= k; j++) {
            dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i])
            dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i])
        }
    }

    return dp[len - 1][k][0]
};

// 状态压缩
var maxProfit = function(k, prices) {
    const len = prices.length;
    if (k == 0 || len < 2) return 0

    if ( k >= len / 2) {
        // 相当于
        // 使用贪心算法求解
        return maxProfitHelp(prices)
    }

    // 1. 状态定义
    // dp[i][k][s] : 表示到下标为 i 的天数为止(从 0 开始)，
    // 发生了 k 次交易次数(从 1 开始)，第 0 次交易的话，利润肯定是 0
    // 状态为 s 的最大利润
    // s = 1 表示持股，s = 0 表示不持股
    const dp = new Array(k + 1).fill(0).map(() => new Array(2).fill(0))

    // 2. 状态初始化，所有的状态初始化为 0
    // 对于第一天：
    for (let j = 1; j <= k; j++) {
        // 第一天不持股的利润
        dp[j][0] = 0
        // 第一天持股的利润
        dp[j][1] = -prices[0]
    }

    // 3. 状态转移
    for (let i = 1; i < len; i++) {
        for (let j = 1; j <= k; j++) {
            dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i])
            dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i])
        }
    }

    return dp[k][0]
};

var maxProfitHelp = function(prices) {
    let res = 0;
    for (let i = 1; i < prices.length; i++) {
        if (prices[i] > prices[i - 1]) {
            res += prices[i] - prices[i - 1]
        }
    }
    return res
}