func minFallingPathSum(matrix [][]int) int {
    var n = len(matrix)
    // dp[i][j]：表示元素 [i, j] 的下降路径最小和
    var dp = make([][]int, n)
    for i := range dp {
        dp[i] = make([]int, n)
    }

    for j := 0; j < n; j++ {
        // 最后一行所有元素的下降路径最小和就是元素值本身
        dp[n - 1][j] = matrix[n - 1][j]
    }

    for i := n - 2; i >= 0; i-- {
        for j := 0; j < n; j++ {
            // dp[i][j] = min(dp[i + 1][j], dp[i + 1][j - 1], dp[i + 1][j + 1])
            var minSum = dp[i + 1][j]
            if j > 0 {
                minSum = min(minSum, dp[i + 1][j - 1])
            }
            if j + 1 < n {
                minSum = min(minSum, dp[i + 1][j + 1])
            }
            dp[i][j] = minSum + matrix[i][j]
        }
    }

    var ans = math.MaxInt32
    for _, x := range dp[0] {
        ans = min(x, ans)
    }

    return ans
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}