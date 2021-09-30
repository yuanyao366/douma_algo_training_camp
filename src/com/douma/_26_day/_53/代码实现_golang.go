// 动态规划 (前缀和解法)
// 时间复杂度：O(n^2) --> 超时
// 空间复杂度：O(n)
func maxSubArray1(nums []int) int {
    // 状态定义：prefixSum[i] 表示子数组 [0, j] 的累加和
    var prefixSum = make([]int, len(nums))

    // 状态初始化
    prefixSum[0] = nums[0]
    var maxSum = prefixSum[0]
    for i := 1; i < len(nums); i++ {
        prefixSum[i] = prefixSum[i - 1] + nums[i]
        if prefixSum[i] > maxSum {
            maxSum = prefixSum[i]
        }
    }

    // 状态转移
    for i := 1; i < len(nums); i++ {
        for j := i; j < len(nums); j++ {
            // [i, j]
            var sum = prefixSum[j] - prefixSum[i - 1]
            if sum > maxSum {
                maxSum = sum
            }
        }
    }

    return maxSum
}

// 动态规划(改变状态定义)
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func maxSubArray2(nums []int) int {
    // 状态定义：dp[i] 表示以索引为 i 的元素结尾的最大子数组和
    var dp = make([]int, len(nums))

    // 状态初始化
    dp[0] = nums[0]
    var maxSum = dp[0]

    // 状态转移
    for i := 1; i < len(nums); i++ {
        dp[i] = max(dp[i - 1] + nums[i], nums[i])
        maxSum = max(maxSum, dp[i])
    }

    return maxSum
}

// 动态规划(改变状态定义) + 状态压缩
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func maxSubArray(nums []int) int {
    var preMaxSum = nums[0]
    var maxSum = preMaxSum

    for i := 1; i < len(nums); i++ {
        preMaxSum = max(preMaxSum + nums[i], nums[i])
        maxSum = max(maxSum, preMaxSum)
    }

    return maxSum
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}