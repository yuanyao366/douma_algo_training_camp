/**
 * @param {number} n
 * @param {number} minProfit
 * @param {number[]} group
 * @param {number[]} profit
 * @return {number}
 */
/*
    二维费用背包问题
        选择一个工作 i ，需要两个代价：
            1. 人数减掉了 group[i]，人数最多为 n
            2. 利润增加 profit[i]，最少的利润为 minProfit
     */
var profitableSchemes = function(n, minProfit, group, profit) {
    MOD = Math.pow(10, 9) + 7

    // dp[j][k]：选择了 j 个员工，并且满足工作利润至少为 k 的情况下的盈利计划的总数目
    const dp = new Array(n + 1).fill(0).map(() => new Array(minProfit + 1).fill(0))
    for (let j = 0; j <= n; j++) {
        dp[j][0] = 1
    }

    for (let i = 1; i <= group.length; i++) {
        const members = group[i - 1], earn = profit[i - 1]
        for (let j = n; j >= members; j--) {
            for (let k = minProfit; k >= 0; k--) {
                // 工作利润至少为 k 而不是工作利润恰好为 k
                // 所以这个是 max(0, k - earn)，而非 k - earn，下面的代码相当于：
                /*
                    if(k <= earn)
                    // 相当于当前的工作产生的利润完全满足第二个代价 (即已经满足工作利润至少为 k 的条件了)
                    dp[j][k] = dp[j][k] + dp[j - members][0];
                else
                    dp[j][k] = dp[j][k] + dp[j - members][k - earn];
                    */
                dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % MOD
            }
        }
    }

    return dp[n][minProfit]
};