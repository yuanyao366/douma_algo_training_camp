// 动态规划
func maximalSquare1(matrix [][]byte) int {
    var m, n, ans = len(matrix), len(matrix[0]), 0

    // dp[i][j] 表示以 [i, j] 这个元素为右下角的最大的正方形的边长 
    var dp = make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    // 第一列
    for i := 0; i < m; i++ {
        if matrix[i][0] == '1' {
            dp[i][0] = 1
            if dp[i][0] > ans {
                ans = dp[i][0]
            }
        }
    }

    // 第一行
    for i := 0; i < n; i++ {
        if matrix[0][i] == '1' {
            dp[0][i] = 1
            if dp[0][i] > ans {
                ans = dp[0][i]
            }
        }
    }

    // 状态转移
    for i := 1; i < m; i++ {
        for j := 1; j < n; j++ {
            if matrix[i][j] == '1' {
                dp[i][j] = min(dp[i][j - 1], min(dp[i - 1][j - 1], dp[i - 1][j])) + 1
                if dp[i][j] > ans {
                    ans = dp[i][j]
                }
            }
        }
    }

    // 返回结果
    return ans * ans
}

func maximalSquare(matrix [][]byte) int {
    var m, n, ans = len(matrix), len(matrix[0]), 0

    // dp[i][j] 表示以 [i, j] 这个元素为右下角的最大的正方形的边长 
    // 行的长度和列的长度都增加 1，有利于边界条件的处理 
    var dp = make([][]int, m + 1)
    for i := range dp {
        dp[i] = make([]int, n + 1)
    }

    // 状态转移
    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if matrix[i - 1][j - 1] == '1' {
                dp[i][j] = min(dp[i][j - 1], min(dp[i - 1][j - 1], dp[i - 1][j])) + 1
                if dp[i][j] > ans {
                    ans = dp[i][j]
                }
            } else {
                // 对于以 0 为右下角的最大正方形边长设置为 0
                // 这里可加可不加，因为 dp[i][j] 初始化的时候就是 0
                dp[i][j] = 0
            }
        }
    }

    // 返回结果
    return ans * ans
}

// 状态压缩：压缩为一维数组
// 计算当前的状态只依赖于上(preRow)、左上 (preRowPreCol) 以及左边 (dp[j - 1]) 三个状态
// 对于 preRow 和 preRowPreCol 的计算逻辑请参考：
// 课程 B 刷题篇第 26 天的力扣 1143 号算法题，视频讲解链接：https://ke.qq.com/course/3614291
func maximalSquare2(matrix [][]byte) int {
    var m, n, ans = len(matrix), len(matrix[0]), 0

    // 状态压缩为一维数组 
    // 行的长度和列的长度都增加 1，有利于边界条件的处理 
    var dp = make([]int, n + 1)


    // 状态转移
    for i := 1; i <= m; i++ {
        var preRow, preRowPreCol = 0, 0
        for j := 1; j <= n; j++ {
            preRowPreCol = preRow
            preRow = dp[j]
            if matrix[i - 1][j - 1] == '1' {
                dp[j] = min(dp[j - 1], min(preRowPreCol, preRow)) + 1
                if dp[j] > ans {
                    ans = dp[j]
                }
            } else {
                // 对于以 0 为右下角的最大正方形边长设置为 0
                // 这里必须加上，因为经过若干个循环， dp[j] 已经不等于 0 了
                dp[j] = 0
            }
        }
    }

    // 返回结果
    return ans * ans
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}