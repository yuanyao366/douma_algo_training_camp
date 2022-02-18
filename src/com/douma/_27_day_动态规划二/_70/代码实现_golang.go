// DFS + 记忆化技术
func climbStairs1(n int) int {
    var memo = make([]int, n + 1)
    for i := range memo {
        memo[i] = -1
    }

    var dfs func(int) int
    dfs = func(n int) int {
        if n == 1 {
            return 1
        }
        if n == 2 {
            return 2
        }
        if memo[n] != -1 {
            return memo[n]
        }
        var left = dfs(n - 1)
        var right = dfs(n -2)
        memo[n] = left + right
        return memo[n]
    }

    return dfs(n)
}

// 动态规划
func climbStairs2(n int) int {
    if n <= 2 {
        return n
    }

    // 1. 定义状态数组，dp[i] 表示的是数字 i 的斐波那契数
    var dp = make([]int, n + 1)

    // 2. 状态初始化
    dp[1] = 1
    dp[2] = 2

    // 3. 状态转移
    for i := 3; i <= n; i++ {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    // 4. 返回最终需要的状态值
    return dp[n]
}

// 动态规划 + 状态压缩
func climbStairs3(n int) int {
    if n <= 2 {
        return n
    }

    var prev, curr = 1, 2

    for i := 3; i <= n; i++ {
        var sum = prev + curr
        prev = curr
        curr = sum
    }

    return curr
}