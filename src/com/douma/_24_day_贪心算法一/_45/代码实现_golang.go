// 贪心
// 贪心策略：每步都选择能跳到的最远距离
func jump1(nums []int) int {
    if len(nums) == 1 {
        return 0
    }

    var steps, start, end = 0, 0, 0
    for end < len(nums) - 1 { // 走到最后一个位置的时候就不用走了
        var maxPos = 0
        // 每次从 [start, end] 中都选择能跳到的最远距离
        for i := start; i <= end; i++ {
            if i + nums[i] > maxPos {
                maxPos = i + nums[i]
            }
        }
        start = end + 1
        end = maxPos
        steps++
    }
    return steps
}

// 贪心
func jump(nums []int) int {
    if len(nums) == 1 {
        return 0
    }

    var steps, maxPos, end = 0, 0, 0
    for i := 0; i < len(nums) - 1; i++ {
        if i + nums[i] > maxPos {
            maxPos = i + nums[i]
        }
        if i == end {
            steps++
            end = maxPos
        }
    }
    return steps
}