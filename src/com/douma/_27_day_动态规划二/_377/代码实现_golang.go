// 完全背包问题：
//      在 nums 列表中可重复的选择数字组合，使得组合之和等于 target
func combinationSum4(nums []int, target int) int {
    // 1. 状态定义：dp[c] : 能够凑成 target 为 c 的组合数。
    var dp = make([]int, target + 1)

    // 2. 状态初始化
    // 凑成 target 为 0 的组合就是不选择任何整数
    dp[0] =1

    // 3. 状态转移
    // 为了不会排除数字相同，但是顺序不同的组合
    // 这里我们针对每一种 target 来选择所有的整数
    for c := 1; c <= target; c++ {
        for i := 0; i < len(nums); i++ {
            if c >= nums[i] {
                dp[c] = dp[c] + dp[c - nums[i]]
            }
        }
    }

    return dp[target]
}