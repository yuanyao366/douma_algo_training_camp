
// 和 leetcode 813 类似
func splitArray(nums []int, m int) int {
    var n = len(nums)
    var prefixSum = make([]int, n + 1)
    for i := 1; i <= n; i++ {
        prefixSum[i] += prefixSum[i - 1] + nums[i - 1]
    }

    // dp[i][j]: 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值
    var dp = make([][]int, n + 1)
    for i := range dp {
        dp[i] = make([]int, m + 1)
        for j := range dp[i] {
            dp[i][j] = math.MaxInt32
        }
    }

    dp[0][0] = 0
    for i := 1; i <= n; i++ {
        for j := 1; j <= m && j <= i; j++ {
            for l := 0; l < i; l++ {
                dp[i][j] = min(dp[i][j], max(dp[l][j - 1], prefixSum[i] - prefixSum[l]))
            }
        }
    }

    return dp[n][m]
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}