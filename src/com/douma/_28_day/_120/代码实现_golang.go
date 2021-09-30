// 记忆化搜索
func minimumTotal1(triangle [][]int) int {
    var n = len(triangle)
    var memo = make([][]int, n)
    for i := range memo {
        memo[i] = make([]int, n)
        for j := range memo[i] {
            memo[i][j] = math.MaxInt32
        }
    }

    var dfs func(int, int) int
    dfs = func(i, j int) int {
        if i == n {
            return 0
        }

        if memo[i][j] != math.MaxInt32 {
            return memo[i][j]
        }

        var left = dfs(i + 1, j)
        var right = dfs(i + 1, j + 1)

        memo[i][j] = min(left, right) + triangle[i][j]
        return memo[i][j]
    }

    return dfs(0, 0)
}

// 动态规划
func minimumTotal2(triangle [][]int) int {
    var n = len(triangle)
    // dp[i][j] 表示从点 [i, j] 到底边的最小路径和。
    var dp = make([][]int, n)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    for i := 0; i < n; i++ {
        dp[n - 1][i] = triangle[n - 1][i]
    }

    // 状态转移
    // bug 修复：从倒数第二行开始
    for i := n - 2; i >= 0; i-- {
        for j := 0; j <= i; j++ {
            dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
        }
    }

    // 返回结果
    return dp[0][0]
}

// 动态规划 + 状态空间压缩
func minimumTotal(triangle [][]int) int {
    var n = len(triangle)

    var dp = make([]int, n)

    for i := 0; i < n; i++ {
        dp[i] = triangle[n - 1][i]
    }

    // 状态转移
    // bug 修复：从倒数第二行开始
    for i := n - 2; i >= 0; i-- {
        for j := 0; j <= i; j++ {
            dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]
        }
    }

    // 返回结果
    return dp[0]
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}