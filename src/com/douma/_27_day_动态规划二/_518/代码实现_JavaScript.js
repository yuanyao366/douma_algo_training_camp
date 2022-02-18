/**
 * @param {number} amount
 * @param {number[]} coins
 * @return {number}
 */
// 转化为完全背包问题：
//     从 coins 列表中可重复选择硬币组合，使得他们的总金额为 amount
//     请问有多少种组合
var change = function(amount, coins) {
    // dp[c]：硬币列表能够凑成总金额为 c 的组合数。
    const dp = new Array(amount + 1).fill(0)
    // 凑成总金额为 0 的组合就是不选择任何硬币
    dp[0] = 1

    for (const coin of coins) {
        // 从 coins[i] 开始即可
        for (let c = coin; c <= amount; c++) {
            dp[c] += dp[c - coin]
        }
    }
    return dp[amount]
};