func maxProfit1(prices []int) int {
    var n = len(prices)
    var dp = make([][3][2]int, n)

    dp[0][1][0] = 0
    dp[0][1][1] = -prices[0]
    dp[0][2][0] = 0
    dp[0][2][1] = -prices[0]

    for i := 1; i < n; i++ {
        dp[i][1][0] = max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i])
        dp[i][1][1] = max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i])
        dp[i][2][0] = max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i])
        dp[i][2][1] = max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i])
    }

    return dp[n - 1][2][0]
}

// 状态压缩
func maxProfit(prices []int) int {
    var n = len(prices)

    var profit10 = 0
    var profit11 = -prices[0]
    var profit20 = 0
    var profit21 = -prices[0]

    for i := 1; i < n; i++ {
        profit10 = max(profit10, profit11 + prices[i])
        profit11 = max(profit11, -prices[i])
        profit20 = max(profit20, profit21 + prices[i])
        profit21 = max(profit21, profit10 - prices[i])
    }

    return profit20
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}