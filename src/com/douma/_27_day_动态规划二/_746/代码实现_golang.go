// 记忆化搜索
func minCostClimbingStairs1(cost []int) int {
    var n = len(cost)
    var memo = make([]int, n + 1)
    for i := range memo {
        memo[i] = -1
    }

    var dfs func(int) int
    dfs = func(i int) int {
        if i == 0 || i == 1 {
            return 0
        }
        if memo[i] != -1 {
            return memo[i]
        }
        var left = dfs(i - 1)
        var right = dfs(i -2)
        memo[i] = min(left + cost[i - 1], right + cost[i - 2])
        return memo[i]
    }

    return dfs(n)
}

// 动态规划
func minCostClimbingStairs2(cost []int) int {
    var n = len(cost)

    // 状态 dp[i]：表示走到第 i 个台阶使用的最小花费
    var dp = make([]int, n + 1)

    // 2. 状态初始化
    dp[0] = 0
    dp[1] = 0

    // 3. 状态转移
    for i := 2; i <= n; i++ {
        dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
    }

    // 4. 返回最终需要的状态值
    return dp[n]
}

// 动态规划  + 状态空间压缩
func minCostClimbingStairs(cost []int) int {
    var n = len(cost)

    var prev, curr = 0, 0

    for i := 2; i <= n; i++ {
        var tmp = min(curr + cost[i - 1], prev + cost[i - 2])
        prev = curr
        curr = tmp
    }

    return curr
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}