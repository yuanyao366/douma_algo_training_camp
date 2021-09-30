func lengthOfLIS(nums []int) int {
    var maxLen = 1
    // 状态：dp[i] 表示以 nums[i] 结尾时最长递增子序列的长度
    var dp = make([]int, len(nums))

    // 状态初始化：单个元素最少有一个递增子序列元素
    for i := range dp {
        dp[i] = 1
    }

    // 代码优化：将 i 和 j 换了一个位置
    for i := 1; i < len(nums); i++ {
        for j := 0; j < i; j++ {
            if nums[i] > nums[j] {
                dp[i] = max(dp[j] + 1, dp[i])
                maxLen = max(maxLen, dp[i])
            }
        }
    }

    return maxLen
}


func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}