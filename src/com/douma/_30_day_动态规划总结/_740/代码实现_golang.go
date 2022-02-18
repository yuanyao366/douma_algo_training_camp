/*
    选择 nums[i] ，然后删除所有的 nums[i] - 1 和 nums[i] + 1
    这个意思就是，偷了 nums[i] 后，就不能偷 nums[i] 前后相邻的两个数了
    所以，问题可以抽象为打家劫舍问题

    算法步骤为：
        1. 先对 nums 中的每个元素进行个数统计，得到 sum 数组，
            存储在一个数组中，元素的值作为 sum 数组的下标，
            相同元素的累加和作为 sum 数组元素的值
        2. 然后对 sum 数组进行打家劫舍
*/
func deleteAndEarn(nums []int) int {
    var maxVal = 0
    for _, val := range nums {
        if val > maxVal {
            maxVal = val
        }
    }

    var sum = make([]int, maxVal + 1)
    for _, val := range nums {
        sum[val] += val
    }

    return rob(sum)
}

 // 198 号算法题：打家劫舍
func rob(nums []int) int {
    var n = len(nums)
    if n == 1 {
        return nums[0]
    }

    // dp[i]：表示偷盗 [0, i] 区间房子得到的最大金额
    var dp = make([]int, n)

    // 2. 状态初始化
    dp[0] = nums[0]
    dp[1] = max(nums[0], nums[1])

    // 3. 状态转移
    for i := 2; i < n; i++ {
        dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
    }

    // 4. 返回最终需要的状态值
    return dp[n - 1]
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}