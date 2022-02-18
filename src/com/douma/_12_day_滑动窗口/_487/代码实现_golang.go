func findMaxConsecutiveOnes(nums []int) int {
    var ans, left, right = 0, 0, 0
    // 记录当前窗口中 0 出现的位置
    var zeroIndex = -1
    for right < len(nums) {
        if nums[right] == 0 {
            if zeroIndex >= 0 { // 说明当前窗口已经有 0
                if right - left > ans {
                    ans = right - left
                }
                left = zeroIndex + 1
            }
            zeroIndex = right
        }
        right++
    }
    if right - left > ans {
        return right - left
    } else {
        return ans
    }
}