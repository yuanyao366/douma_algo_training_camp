func maxProfit1(prices []int, fee int) int {
    var dp = make([][2]int, len(prices))

    dp[0][0] = 0
    dp[0][1] = -prices[0] - fee

    for i := 1; i < len(prices); i++ {
        dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
        dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)
    }
    return dp[len(prices) - 1][0]
}

func maxProfit(prices []int, fee int) int {
    var profit0, profit1 = 0, -prices[0] - fee

    for i := 1; i < len(prices); i++ {
        profit0 = max(profit0, profit1 + prices[i])
        profit1 = max(profit1, profit0 - prices[i] - fee)
    }
    return profit0
}


func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}