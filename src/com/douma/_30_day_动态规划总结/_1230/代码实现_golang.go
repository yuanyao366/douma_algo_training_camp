func probabilityOfHeads(prob []float64, target int) float64 {
    var n = len(prob)

    // dp[i][j] ：表示前 i 枚硬币抛掷正面 j 次的概率
    var dp = make([][]float64, n + 1)
    for i := range dp {
        dp[i] = make([]float64, target + 1)
    }

    dp[0][0] = 1
    for i := 1; i <= n; i++ {
        // 当前投掷中有 0 次为正面，一种可能是上一次投掷就已经是 0 次正面了，本次投掷结果是反面
        dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1])
    }

    for i := 1; i <= n; i++ {
        for j := 1; j <= target; j++ {
            // 当前投掷中有 j 次为正面，一种可能是上一次投掷就已经是 j 次正面了，本次投掷结果是反面
            dp[i][j] = dp[i - 1][j] * (1 - prob[i - 1])

            // 前一次有 j - 1 次为正面，本次投掷为正面的可能性
            dp[i][j] += dp[i - 1][j - 1] * prob[i - 1]
        }
    }

    return dp[n][target]
}