func rob(nums []int) int {
    var n = len(nums)
    if n == 1 {
        return nums[0]
    }
    var notRobLastHouse = robHelp(nums, 0, n - 2)
    var notRobFirstHouse = robHelp(nums, 1, n - 1)
    return max(notRobLastHouse, notRobFirstHouse)
}

// 动态规划 + 状态压缩
func robHelp(nums []int, start int, end int) int {
    var n = len(nums)
    if n == 1 {
        return nums[0]
    }

    var prevMax, currMax = 0, 0

    for i := start; i <= end; i++ {
        var tmpMax = max(currMax, prevMax + nums[i])
        prevMax = currMax
        currMax = tmpMax
    }

    return currMax
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}