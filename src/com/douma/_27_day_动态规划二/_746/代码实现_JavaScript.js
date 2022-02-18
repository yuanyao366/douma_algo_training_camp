/**
 * @param {number[]} cost
 * @return {number}
 */
// 记忆化搜索
var minCostClimbingStairs1 = function(cost) {
    const n = cost.length
    const memo = new Array(n + 1).fill(-1)

    const dfs = (i) => {
        if (i == 0 || i == 1) return 0

        if (memo[i] != -1) return memo[i]

        const left = dfs(i - 1)
        const right = dfs(i - 2)

        memo[i] = Math.min(left + cost[i - 1], right + cost[i - 2])
        return memo[i]
    }

    return dfs(n)
};

// 动态规划
var minCostClimbingStairs2 = function(cost) {
    const n = cost.length
    const dp = new Array(n + 1).fill(-1)

    dp[0] = dp[1] = 0

    for (let i = 2; i <= n; i++) {
        dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
    }

    return dp[n]
}

// 动态规划 + 状态转移
var minCostClimbingStairs = function(cost) {
    const n = cost.length

    let prev = 0, curr = 0

    for (let i = 2; i <= n; i++) {
        const sum = Math.min(curr + cost[i - 1], prev + cost[i - 2])
        prev = curr
        curr = sum
    }

    return curr
}