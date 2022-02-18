// 贪心策略：每步都选择能跳到的最远距离
func canJump(nums []int) bool {
    var maxPos = 0
    for i := 0; i < len(nums); i++ {
        if maxPos < i {
            return false
        }
        if i + nums[i] > maxPos {
            maxPos = i + nums[i]
        }
    }
    return true
}