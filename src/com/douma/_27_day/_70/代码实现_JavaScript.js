/**
 * @param {number} n
 * @return {number}
 */
// 1. 记忆化搜索
var climbStairs1 = function(n) {
    const memo = new Array(n + 1).fill(-1)

    const dfs = (n) => {
        if (n == 1 || n == 2) return n

        if (memo[n] != -1) return memo[n]

        const left = dfs(n - 1)
        const right = dfs(n - 2)

        memo[n] = left + right
        return memo[n]
    }

    return dfs(n)
};

// 2. 动态规划
var climbStairs2 = function(n) {
    if (n == 1 || n == 2) return n

    const dp = new Array(n + 1).fill(0)

    dp[1] = 1
    dp[2] = 2

    for (let i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    return dp[n]
}

// 2. 动态规划 + 状态压缩
var climbStairs = function(n) {
    if (n == 1 || n == 2) return n

    let prev = 1
    let curr = 2

    for (let i = 3; i <= n; i++) {
        const sum = prev + curr
        prev = curr
        curr = sum
    }

    return curr
}