/**
 * @param {number} n
 * @return {number}
 */
// 完全背包问题
// 完全平方数最小为 1，最大为 sqrt(n)
// 也就是我们要从 nums = [1, 2, ..., sqrt(n)] 数组里选出几个数，令其平方和为 target = n。
// 转化为是否可以用 nums 中的数(可重复选用)组合和成 n
var numSquares = function(n) {
    MAX_INT = Math.pow(2, 31) - 1
    // dp[i] 表示和为 i 的 nums 组合中完全平方数最少个数
    const dp = new Array(n + 1).fill(MAX_INT)
    dp[0] = 0

    for (let num = 1; num <= parseInt(Math.sqrt(n) + 1); num++) {
        for (let c = num * num; c <= n; c++) {
            dp[c] = Math.min(dp[c], dp[c - num * num] + 1)
        }
    }

    return dp[n]
};