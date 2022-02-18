func longestOnes(nums []int, k int) int {
    var ans, left, right = 0, 0, 0
    // 记录当前窗口中 1 的个数
    var oneCnt = 0
    for right < len(nums) {
        if nums[right] == 1 {
            oneCnt++
        }
        for (right - left + 1) - oneCnt > k {
            if nums[left] == 1 {
                oneCnt--
            }
            left++
        }
        if right - left + 1 > ans {
            ans = right - left + 1
        }
        right++
    }
    return ans
}