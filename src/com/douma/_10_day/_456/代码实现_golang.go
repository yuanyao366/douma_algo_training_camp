// 方法一：暴力解法
// 时间复杂度：O(n^3) --> 超时
// 空间复杂度：O(1)
func find132pattern1(nums []int) bool {
    var n = len(nums)
    if n < 3 {
        return false
    }
    for i := 0; i < n; i++ {
        for j := i + 1; j < n; j++ {
            for k := j + 1; k < n; k++ {
                if nums[i] < nums[k] && nums[k] < nums[j] {
                    return true
                }
            }
        }
    }
    return false
}

// 方法二：优化，维护最小的 nums[i]
// 时间复杂度：O(n^2) --> 超时
// 空间复杂度：O(1)
func find132pattern2(nums []int) bool {
    var n = len(nums)
    if n < 3 {
        return false
    }
    var numsi = nums[0]
    for j := 1; j < n; j++ {
        // 线性查找
        for k := j + 1; k < n; k++ {
            if numsi < nums[k] && nums[k] < nums[j] {
                return true
            }
        }
        // 维护最小的 nums[i]
        if nums[j] < numsi {
            numsi = nums[j]
        }
    }
    return false
}

// 方法三：前缀最小值数组 + 单调栈
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func find132pattern3(nums []int) bool {
    var n = len(nums)
    if n < 3 {
        return false
    }
    // 维护一个前缀最小值数组，用于确定 nums[i]
    var minPrefix = make([]int, n)
    minPrefix[0] = nums[0]
    for i := 1; i < n; i++ {
        minPrefix[i] = min(minPrefix[i - 1], nums[i])
    }

    var stack = []int{nums[n - 1]}
    // bug 修复：j 从倒数第二个元素开始
    for j := n - 2; j >= 1; j-- {
        if nums[j] > minPrefix[j] {
            for len(stack) > 0 && stack[len(stack) - 1] <= minPrefix[j] {
                stack = stack[:len(stack) - 1]
            }
            if len(stack) > 0 && stack[len(stack) - 1] < nums[j] {
                return true
            }
            stack = append(stack, nums[j])
        }
    }
    return false
}


// 方法四：单调栈
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func find132pattern(nums []int) bool {
    var n = len(nums)
    if n < 3 {
        return false
    }
    var stack, maxk = []int{nums[n - 1]}, math.MinInt32
    for i := n - 2; i >= 0; i-- {
        if nums[i] < maxk {
            return true
        }
        for len(stack) > 0 && nums[i] > stack[len(stack) - 1] {
            maxk = stack[len(stack) - 1]
            stack = stack[:len(stack) - 1]
        }
        if nums[i] > maxk {
            stack = append(stack, nums[i])
        }
    }
    return false
}


func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}