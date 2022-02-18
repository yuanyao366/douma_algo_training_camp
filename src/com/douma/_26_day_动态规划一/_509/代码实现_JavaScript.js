/**
 * @param {number} n
 * @return {number}
 */
// DFS
// 时间复杂度：O(2^n)
// 因为 n 最大为 30，比较小，所以不会超时
// 空间复杂度：O(logn) 递归系统栈需要的空间开销
var fib1 = function(n) {

    const dfs = (n) => {
        if (n == 0) return 0
        if (n == 1) return 1

        const leftFib = dfs(n - 1)
        const rightFib = dfs(n -2)

        return leftFib + rightFib
    }

    return dfs(n)
};

// DFS + 记忆化搜索
// 时间复杂度：O(n)
// 空间复杂度：O(n)
var fib2 = function(n) {
    const memo = new Array(n + 1).fill(-1)

    const dfs = (n) => {
        if (n == 0) return 0
        if (n == 1) return 1
        if (memo[n] != -1) return memo[n]
        const leftFib = dfs(n - 1)
        const rightFib = dfs(n -2)
        memo[n] = leftFib + rightFib
        return memo[n]
    }

    return dfs(n)
};

// 动态规划
var fib3 = function(n) {
    if (n <= 1) return n

    // 1. 定义状态数组，dp[i] 表示的是数字 i 的斐波那契数
    const dp = new Array(n + 1).fill(0)

    // 2. 状态初始化
    dp[0] = 0
    dp[1] = 1

    // 3. 状态转移
    for (let i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    // 4. 返回最终需要的状态值
    return dp[n]
}


// 动态规划 + 状态压缩
var fib = function(n) {
    if (n <= 1) return n

    let prev = 0, curr = 1

    for (let i = 2; i <= n; i++) {
        const sum = prev + curr
        prev = curr
        curr = sum
    }

    return curr
}