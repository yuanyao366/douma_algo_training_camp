// 假设数组中所有数字的总和为 sum
// 假设前面设置为负数的数字的总和是 neg，那么设置为正数的数字的总和为 sum - neg
// (sum - neg) - neg = target => neg = (sum - target) / 2
// 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg(背包容量)
// 求有多少组合数？
// 0 - 1 背包问题
func findTargetSumWays(nums []int, target int) int {
    var sum = 0
    for _, num := range nums {
        sum += num
    }

    var diff = sum - target
    if diff < 0 || diff % 2 == 1 {
        return 0
    }

    var neg = diff / 2

    // 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg
    // 求有多少组合数？
    var dp = make([]int, neg + 1)
    dp[0] = 1
    for i := range nums {
        for c := neg; c >= nums[i]; c-- {
            dp[c] = dp[c] + dp[c - nums[i]]
        }
    }

    return dp[neg]
}