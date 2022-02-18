// 暴力解法
// 时间复杂度：O(nk) 超时
func findMaxAverage1(nums []int, k int) float64 {
    var maxSum = math.MinInt32
    for i := 0; i < len(nums) - k + 1; i++ {
        var sum = 0
        // 存在重复计算
        for j := i; j < i + k; j++ {
            sum += nums[j]
        }
        if sum > maxSum {
            maxSum = sum
        }
    }
    return float64(maxSum) / float64(k)
}

// 前缀和优化(空间换时间)
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func findMaxAverage2(nums []int, k int) float64 {
    var prefixSum = make([]int, len(nums) + 1)
    prefixSum[0] = 0
    for i := 1; i <= len(nums); i++ {
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1]
    }

    var maxSum = math.MinInt32
    for i := 0; i < len(nums) - k + 1; i++ {
        var sum = prefixSum[i + k] - prefixSum[i]
        if sum > maxSum {
            maxSum = sum
        }
    }
    return float64(maxSum) / float64(k)
}


// 滑动窗口
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func findMaxAverage(nums []int, k int) float64 {
    var maxSum = math.MinInt32
    // left 是窗口的左边界，right 是窗口的右边界
    var left, right, windowSum = 0, 0, 0
    for right < len(nums) {
        windowSum += nums[right]
        // 满足窗口的条件：达到了固定的窗口大小
        if right - left + 1 == k {
            if windowSum > maxSum {
                maxSum = windowSum
            }
            windowSum -= nums[left]
            left++
        }
        right++
    }
    return float64(maxSum) / float64(k)
}





