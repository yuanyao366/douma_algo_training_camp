// 动态规划
func maxProduct1(nums []int) int {
    var length = len(nums)
    var maxP = make([]int, length)
    var minP = make([]int, length)

    maxP[0] = nums[0]
    minP[0] = nums[0]
    var ans = nums[0]

    for i := 1; i < length; i++ {
        maxP[i] = max(maxP[i - 1] * nums[i], max(nums[i], minP[i - 1] * nums[i]))
        minP[i] = min(minP[i - 1] * nums[i], min(nums[i], maxP[i - 1] * nums[i]))
        ans = max(ans, maxP[i])
    }

    return ans
}

// 动态规划 + 状态的压缩
func maxProduct(nums []int) int {
    var length = len(nums)
    var maxP = nums[0]
    var minP = nums[0]
    var ans = nums[0]

    for i := 1; i < length; i++ {
        var mx, mn = maxP, minP
        maxP = max(mx * nums[i], max(nums[i], mn * nums[i]))
        minP = min(mn * nums[i], min(nums[i], mx * nums[i]))
        ans = max(ans, maxP)
    }

    return ans
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