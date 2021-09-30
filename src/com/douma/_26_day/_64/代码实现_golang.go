var dirs = [][]int{{1, 0}, {0, 1}}

// 3. 记忆化搜索
func minPathSum1(grid [][]int) int {
    var m, n = len(grid), len(grid[0])
    var memo = make([][]int, m)
    for i := range memo {
        memo[i] = make([]int, n)
        for j := range memo[i] {
            memo[i][j] = math.MaxInt32
        }
    }

    var dfs func(int, int) int
    dfs = func(row, col int) int {
        if row == m - 1 && col == n - 1 {
            return grid[row][col]
        }

        if memo[row][col] != math.MaxInt32 {
            return memo[row][col]
        }

        var minPathSum = math.MaxInt32
        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            if nextRow < 0 || nextCol < 0 || nextRow >= m || nextCol >= n {
                continue
            }
            var childMinPathSum = dfs(nextRow, nextCol)
            if childMinPathSum < minPathSum {
                minPathSum = childMinPathSum
            }
        }
        memo[row][col] = minPathSum + grid[row][col]
        return memo[row][col]
    }

    return dfs(0, 0)
}


// 4. 动态规划：从终点到起始点
func minPathSum2(grid [][]int) int {
    var m, n = len(grid), len(grid[0])

    // 状态定义：dp[i][j] 表示从坐标 (i, j) 到右下角的最小路径和
    var dp = make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    // 状态初始化
    dp[m - 1][n - 1] = grid[m - 1][n - 1]

    for i := m - 1; i >= 0; i-- {
        for j := n - 1; j >= 0; j-- {
            if i == m - 1 && j != n - 1 {
                dp[i][j] = grid[i][j] + dp[i][j + 1]
            } else if i != m - 1 && j == n - 1 {
                dp[i][j] = grid[i][j] + dp[i + 1][j]
            } else if i != m - 1 && j != n - 1 {
                dp[i][j] = grid[i][j] + min(dp[i + 1][j], dp[i][j + 1])
            }
        }
    }

    // 返回结果
    return dp[0][0]
}

// 5. 动态规划：从起始点到终点
func minPathSum3(grid [][]int) int {
    var m, n = len(grid), len(grid[0])

    // 状态定义：dp[i][j] 表示从 [0,0] 到 [i,j] 的最小路径和
    var dp = make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    // 状态初始化
    dp[0][0] = grid[0][0]

    // 状态转移
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if i == 0 && j != 0 {
                dp[i][j] = grid[i][j] + dp[i][j - 1]
            } else if i != 0 && j == 0 {
                dp[i][j] = grid[i][j] + dp[i - 1][j]
            } else if i != 0 && j != 0 {
                dp[i][j] = grid[i][j] + min(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    // 返回结果
    return dp[m - 1][n - 1]
}

// 6. 动态规划：从起始点到终点 + 状态压缩
func minPathSum4(grid [][]int) int {
    var m, n = len(grid), len(grid[0])

    // 状态定义：dp[i] 表示从 (0, 0) 到达第 i - 1 行的最短路径值
    var dp = make([]int, n)

    // 状态初始化
    dp[0] = grid[0][0]

    // 状态转移
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if i == 0 && j != 0 {
                dp[j] = grid[i][j] + dp[j - 1]
            } else if i != 0 && j == 0 {
                dp[j] = grid[i][j] + dp[j]
            } else if i != 0 && j != 0 {
                dp[j] = grid[i][j] + min(dp[j], dp[j - 1])
            }
        }
    }

    // 返回结果
    return dp[n - 1]
}

// 7. 动态规划：从起始点到终点 + 使用输入数组作为状态数组
func minPathSum(grid [][]int) int {
    var m, n = len(grid), len(grid[0])

    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if i == 0 && j != 0 {
                grid[i][j] = grid[i][j] + grid[i][j - 1]
            } else if i != 0 && j == 0 {
                grid[i][j] = grid[i][j] + grid[i - 1][j]
            } else if i != 0 && j != 0 {
                grid[i][j] = grid[i][j] + min(grid[i - 1][j], grid[i][j - 1])
            }
        }
    }

    // 返回结果
    return grid[m - 1][n - 1]
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}