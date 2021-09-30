// 1. 记忆化搜索
func uniquePaths1(m int, n int) int {
    var memo = make([][]int, m)
    for i := range memo {
        memo[i] = make([]int, n)
        for j := range memo[i] {
            memo[i][j] = -1
        }
    }

    var dfs func(int, int) int
    dfs = func(i, j int) int {
        if i == m - 1 && j == n - 1 {
            return 1
        }
        if i == m || j == n {
            return 0
        }

        if memo[i][j] != -1 {
            return memo[i][j]
        }

        var left = dfs(i, j + 1)
        var right = dfs(i + 1, j)

        memo[i][j] = left + right
        return memo[i][j]
    }

    return dfs(0, 0)
}

// 2. 动态规划（左上到右下）
func uniquePaths2(m int, n int) int {
    // dp[i][j]：表示从位置 [i, j] 到 [m - 1, n - 1] 的路径数
    var dp = make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    for i := m - 1; i >= 0; i-- {
        for j := n - 1; j >= 0; j-- {
            if i == m - 1 || j == n - 1 {
                dp[i][j] = 1
            } else {
                dp[i][j] = dp[i][j + 1] + dp[i + 1][j]
            }
        }
    }

    return dp[0][0]
}

// 3. 动态规划（左上到右下） - 状态压缩
func uniquePaths3(m int, n int) int {
    // dp[i][j]：表示从位置 [i, j] 到 [m - 1, n - 1] 的路径数
    var dp = make([]int, n)

    for i := m - 1; i >= 0; i-- {
        for j := n - 1; j >= 0; j-- {
            if i == m - 1 || j == n - 1 {
                dp[j] = 1
            } else {
                dp[j] = dp[j + 1] + dp[j]
            }
        }
    }

    return dp[0]
}

// 4. 动态规划(右下到左上)
func uniquePaths4(m int, n int) int {
    // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
    var dp = make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    // 状态转移
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if i == 0 || j == 0 {
                dp[i][j] = 1
            } else if i != 0 && j != 0{
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
    }

    // 返回结果
    return dp[m - 1][n - 1]
}

// 动态规划(右下到左上) - 状态压缩
func uniquePaths(m int, n int) int {
    // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
    var dp = make([]int, n)

    // 状态转移
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if i == 0 || j == 0 {
                dp[j] = 1
            } else if i != 0 && j != 0{
                dp[j] = dp[j] + dp[j - 1]
            }
        }
    }

    // 返回结果
    return dp[n - 1]
}