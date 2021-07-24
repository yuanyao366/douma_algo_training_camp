// 转化为完全背包问题：
//     从 coins 列表中可重复选择最少数量的硬币，使得他们的总金额为 amount
var coinChange = function(coins, amount) {
    // 1. 状态定义：dp[c] 表示凑齐总金额为 c 的时候需要的最小硬币数
    const dp = new Array(amount + 1).fill(amount + 1)
    // 凑齐总金额为 0 的时候需要的最小硬币数就是不取硬币
    dp[0] = 0

    for (const coin of coins) {
        for (let c = coin; c <= amount; c++) {
            dp[c] = Math.min(dp[c], 1 + dp[c - coin])
        }
    }
    return dp[amount] == amount + 1 ? -1 : dp[amount]
}