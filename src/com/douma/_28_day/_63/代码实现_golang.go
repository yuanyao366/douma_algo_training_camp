// 1. 动态规划
func uniquePathsWithObstacles1(obstacleGrid [][]int) int {
    var m, n = len(obstacleGrid), len(obstacleGrid[0])

    // 1. 状态定义
    // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
    var dp = make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    // 2. 状态初始化
    // 2.1. 左上角元素
    if obstacleGrid[0][0] == 0 {
        dp[0][0] = 1
    }
    // 2.2：初始化第一列所有点到终点的路径数
    for i := 1; i < m; i++ {
        if obstacleGrid[i][0] == 0 && dp[i - 1][0] == 1 {
            dp[i][0] = 1
        }
    }
    // 2.3：初始化第一行所有点到终点的路径数
    for i := 1; i < n; i++ {
        if obstacleGrid[0][i] == 0 && dp[0][i - 1] == 1 {
            dp[0][i] = 1
        }
    }

    // 状态转移
    for i := 1; i < m; i++ {
        for j := 1; j < n; j++ {
            if obstacleGrid[i][j] == 1 {
                dp[i][j] = 0
                continue
            }
            dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
        }
    }

    // 返回结果
    return dp[m - 1][n - 1]
}

// 2. 动态规划
func uniquePathsWithObstacles2(obstacleGrid [][]int) int {
    var m, n = len(obstacleGrid), len(obstacleGrid[0])

    // 1. 状态定义
    // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
    var dp = make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    // 2. 状态初始化

    // 状态转移
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if obstacleGrid[i][j] == 1 {
                dp[i][j] = 0
                continue
            }
            if i == 0 && j == 0 {
                dp[i][j] = 1
            } else if j == 0 {
                dp[i][j] = dp[i - 1][j]
            } else if i == 0 {
                dp[i][j] = dp[i][j - 1]
            } else {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
            }
        }
    }

    // 返回结果
    return dp[m - 1][n - 1]
}

// 动态规划 + 状态压缩
func uniquePathsWithObstacles(obstacleGrid [][]int) int {
    var m, n = len(obstacleGrid), len(obstacleGrid[0])

    // 1. 状态定义
    // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
    var dp = make([]int, n)

    // 2. 状态初始化

    // 状态转移
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if obstacleGrid[i][j] == 1 {
                dp[j] = 0
                continue
            }
            if i == 0 && j == 0 {
                dp[j] = 1
            } else if j == 0 {
                dp[j] = dp[j]
            } else if i == 0 {
                dp[j] = dp[j - 1]
            } else {
                dp[j] = dp[j - 1] + dp[j]
            }
        }
    }

    // 返回结果
    return dp[n - 1]
}