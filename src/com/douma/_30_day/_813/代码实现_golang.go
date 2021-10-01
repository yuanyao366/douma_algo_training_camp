func largestSumOfAverages(nums []int, k int) float64 {
    var n = len(nums)
    var prefixSum = make([]float64, n + 1)
    for i := 1; i <= n; i++ {
        prefixSum[i] += prefixSum[i - 1] + float64(nums[i - 1])
    }

     // dp[i][j]: 表示将 nums 的前 i 个元素分割成 j 份得到的最大平均分数
    var dp = make([][]float64, n + 1)
    for i := range dp {
        dp[i] = make([]float64, k + 1)
    }

    for i := 1; i <= n; i++ {
        // 将 nums 的前 i 个元素分割成 1 份得到的最大分数
        dp[i][1] = prefixSum[i] / float64(i)
        for j := 2; j <= k && j <= i; j++ {
            // 将 nums 的前 i 个元素分割成 j 份得到的最大平均分数等于：
            // 所有将前 l 个字符分割成 j - 1 份得到的最大平均分数，再加上 [l...i] 的平均值，求最大值
            for l := 0; l < i; l++ {
                dp[i][j] = max(dp[i][j], dp[l][j - 1] + (prefixSum[i] - prefixSum[l]) / float64(i - l))
            }
        }
    }

    return dp[n][k]
}

func max(a, b float64) float64 {
    if a > b {
        return a
    }
    return b
}