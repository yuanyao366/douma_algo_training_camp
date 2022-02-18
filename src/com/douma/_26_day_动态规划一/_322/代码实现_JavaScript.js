/**
 * @param {number[]} coins
 * @param {number} amount
 * @return {number}
 */
// 回溯 + 记忆化搜索
var coinChange1 = function(coins, amount) {
    const MAX_INT = Math.pow(2, 31) - 1
    const memo = new Array(amount + 1).fill(MAX_INT)

    // 计算返回凑成总金额 target 需要的最少硬币数
    const dfs = (target) => {
        if (target == 0) return 0
        if (memo[target] != MAX_INT) return memo[target]

        let minCoins = MAX_INT
        for (const coin of coins) {
            if (target < coin) continue
            const subMinCoins = dfs(target - coin)
            if (subMinCoins == -1) continue
            minCoins = Math.min(minCoins, subMinCoins + 1)
        }
        memo[target] = minCoins == MAX_INT ? -1 : minCoins
        return memo[target]
    }

    return dfs(amount)
};


// 动态规划
var coinChange = function(coins, amount) {
    if (amount < 0) return -1;
    if (amount == 0) return 0;

    const MAX_INT = Math.pow(2, 31) - 1

    // 1. 状态定义：dp[i] 表示凑齐总金额为 i 的时候需要的最小硬币数
    // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
    const dp = new Array(amount + 1).fill(MAX_INT)

    // 2. 状态初始化
    dp[0] = 0

    // 3. 状态转移
    for (let target = 1; target <= amount; target++) {
        for (const coin of coins) {
            if (target >= coin && dp[target - coin] != MAX_INT) {
                dp[target] = Math.min(dp[target], dp[target - coin] + 1);
            }
        }
    }

    // 4. 返回最终需要的状态值
    return dp[amount] == MAX_INT ? -1 : dp[amount]
}
