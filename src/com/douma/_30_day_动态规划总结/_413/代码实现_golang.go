func numberOfArithmeticSlices(nums []int) int {
    var n, sum = len(nums), 0

    // dp[i]：表示数组区间 [0...i] 中等差数组的子数组个数
    // 注意：必须以 nums[i] 结尾，也就说必须选择 nums[i]
    var dp = make([]int, n)

    for i := 2; i < n; i++ {
        if nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2] {
            dp[i] = dp[i - 1] + 1
            sum += dp[i]
        }
    }

    return sum
}