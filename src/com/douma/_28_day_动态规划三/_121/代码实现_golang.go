func maxProfit1(prices []int) int {
    var dp = make([][2]int, len(prices))

    dp[0][0] = 0
    dp[0][1] = -prices[0]

    for i := 1; i < len(prices); i++ {
        dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
        // 在这里有不少同学会认为下面的一行代码等价于：dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        // 注意：不能直接这样将三维压成二维，如果是三维的话应该是这样的：
        // dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
        // 因为 dp[i - 1][0][0] = 0，所以代码变为：dp[i][1][1] = Math.max(dp[i - 1][1][1],  - prices[i]);
        // 这个时候才可以将三维压成二维，即变成下面的代码
        dp[i][1] = max(dp[i - 1][1], -prices[i])
    }
    return dp[len(prices) - 1][0]
}

// 状态压缩
func maxProfit(prices []int) int {
    var profit0, profit1 = 0, -prices[0]

    for i := 1; i < len(prices); i++ {
        profit0 = max(profit0, profit1 + prices[i])
        profit1 = max(profit1, -prices[i])
    }
    return profit0
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}