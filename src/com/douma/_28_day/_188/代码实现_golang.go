/*
    dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
    dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
*/
func maxProfit1(k int, prices []int) int {
    var n = len(prices)
    if k == 0 || n < 2 {
        return 0
    }
    if k >= n / 2 {
        // 问题变成了 122 号算法题，相当于 k 无限大
        return maxProfitGreedy(prices)
    }

    var dp = make([][][2]int, n)
    for i := range dp {
        dp[i] = make([][2]int, k + 1)
    }

    for j := 1; j <= k; j++ {
        dp[0][j][0] = 0
        dp[0][j][1] = -prices[0]
    }

    for i := 1; i < n; i++ {
        for j := 1; j <= k; j++ {
            dp[i][j][0] = max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i])
            dp[i][j][1] = max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i])
        }
    }

    return dp[n - 1][k][0]
}

// 状态压缩
func maxProfit(k int, prices []int) int {
    var n = len(prices)
    if k == 0 || n < 2 {
        return 0
    }
    if k >= n / 2 {
        // 问题变成了 122 号算法题，相当于 k 无限大
        return maxProfitGreedy(prices)
    }

    var dp = make([][2]int, k + 1)

    for j := 1; j <= k; j++ {
        dp[j][0] = 0
        dp[j][1] = -prices[0]
    }

    for i := 1; i < n; i++ {
        for j := 1; j <= k; j++ {
            dp[j][0] = max(dp[j][0], dp[j][1] + prices[i])
            dp[j][1] = max(dp[j][1], dp[j - 1][0] - prices[i])
        }
    }

    return dp[k][0]
}

func maxProfitGreedy(prices []int) int {
    var res = 0
    for i := 1; i < len(prices); i++ {
        if prices[i] > prices[i - 1] {
            res += prices[i] - prices[i - 1]
        }
    }
    return res
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}