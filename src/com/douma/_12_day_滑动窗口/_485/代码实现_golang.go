// 方法一：直接模拟
func findMaxConsecutiveOnes1(nums []int) int {
    var ans, ones = 0, 0
    for _, num := range nums {
        if num == 1 {
            ones++
        } else {
            if ones > ans {
                ans = ones
            }
            ones = 0
        }
    }
    if ones > ans {
        return ones
    } else {
        return ans
    }
}

// 方法二：滑动窗口
func findMaxConsecutiveOnes(nums []int) int {
    var ans, left, right = 0, 0, 0
    for right < len(nums) {
        if nums[right] == 0 {
            if right - left > ans {
                ans = right - left
            }
            left = right + 1
        }
        right++
    }
    if right - left > ans {
        return right - left
    } else {
        return ans
    }
}