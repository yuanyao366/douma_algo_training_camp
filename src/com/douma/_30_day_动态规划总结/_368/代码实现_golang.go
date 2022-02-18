func largestDivisibleSubset(nums []int) []int {
    var n = len(nums)
    sort.Ints(nums)

    // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数

    // 经典 LIS 问题
    // dp[i]：表示以 nums[i] 结尾最大整除子集的大小
    var dp = make([]int, n)
    for i := range dp {
        dp[i] = 1
    }
    var maxSize, maxVal = 1, dp[0]
    for i := 1; i < n; i++ {
        for j := 0; j < i; j++ {
            // 题目中说「没有重复元素」很重要
            if nums[i] % nums[j] == 0 {
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }
        if dp[i] > maxSize {
            maxSize = dp[i]
            maxVal = nums[i]
        }
    }

    // 第 2 步：倒推获得最大子集
    var res = make([]int, 0)
    if maxSize == 1 {
        res = append(res, nums[0])
        return res
    }

    for i := n - 1; i >= 0 && maxSize > 0; i-- {
        if dp[i] == maxSize && maxVal % nums[i] == 0 {
            res = append(res, nums[i])
            maxVal = nums[i]
            maxSize--
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