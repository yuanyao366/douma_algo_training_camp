// 方法一：枚举宽
// 时间复杂度：O(n^2)  --> 超时
// 空间复杂度：O(n)
func largestRectangleArea1(heights []int) int {
    var ans = 0
    for left := range heights {
        var minHeight = heights[left]
        for right := left; right < len(heights); right++ {
            if heights[right] < minHeight {
                minHeight = heights[right]
            }
            // [left, right]
            var currWidth = right - left + 1
            var area = minHeight * currWidth
            if area > ans {
                ans = area
            }
        }
    }
    return ans
}

// 方法二：枚举高
// 时间复杂度：O(n^2)  --> 超时
// 空间复杂度：O(n)
func largestRectangleArea2(heights []int) int {
    var ans = 0
    for mid := range heights {
        var height = heights[mid]
        // 确定左右边界
        var left, right = mid, mid
        for left >= 0 && heights[left] >= height {
            left--
        }
        for right < len(heights) && heights[right] >= height {
            right++
        }
        var area = height * (right - left - 1)
        if area > ans {
            ans = area
        }
    }
    return ans
}

// 方法三：枚举高 + 单调栈优化
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func largestRectangleArea3(heights []int) int {
    var n = len(heights)
    // 1. 计算每根柱子左边第一个小于这根柱子的柱子(每根柱子的左边界)
    var left, stack = make([]int, n), []int{}
    for i := n - 1; i >= 0; i-- {
        left[i] = -1
        for len(stack) > 0 && heights[i] < heights[stack[len(stack) - 1]] {
            left[stack[len(stack) - 1]] = i
            stack = stack[:len(stack) - 1]
        }
        stack = append(stack, i)
    }

    // 2. 计算每根柱子右边第一个小于这根柱子的柱子(每根柱子的右边界)
    var right = make([]int, n)
    stack = []int{}
    for i := 0; i < n; i++ {
        right[i] = n
        for len(stack) > 0 && heights[i] < heights[stack[len(stack) - 1]] {
            right[stack[len(stack) - 1]] = i
            stack = stack[:len(stack) - 1]
        }
        stack = append(stack, i)
    }

    var ans = 0
    for mid := range heights {
        var height = heights[mid]
        var area = height * (right[mid] - left[mid] - 1)
        if area > ans {
            ans = area
        }
    }
    return ans
}

// 方法四：枚举高 + 单调栈优化 + 一次遍历
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func largestRectangleArea(heights []int) int {
    var n = len(heights)
    // 1. 计算每根柱子左边第一个小于这根柱子的柱子(每根柱子的左边界)
    var left, stack = make([]int, n), []int{}
    var right = make([]int, n)
    stack = []int{}
    for i := 0; i < n; i++ {
        // bug 修复：将右边界都初始化为 n
        // 因为没有右边界的需要设置为 n
        right[i] = n
        // 注意：如果出现 heights[i] == heights[stack.peek()] 的情况
        // 那么两个柱子计算出来的面试是一样大的，不管弹不弹出栈顶元素的话
        // 都会有一个柱子的面积计算小了，而另一个柱子面积计算正确
        // 因为我们求的是最大面积，所以这就够了
        for len(stack) > 0 && heights[i] <= heights[stack[len(stack) - 1]] {
            right[stack[len(stack) - 1]] = i
            stack = stack[:len(stack) - 1]
        }
        if len(stack) == 0 {
            left[i] = -1
        } else {
            left[i] = stack[len(stack) - 1]
        }
        stack = append(stack, i)
    }

    var ans = 0
    for mid := range heights {
        var height = heights[mid]
        var area = height * (right[mid] - left[mid] - 1)
        if area > ans {
            ans = area
        }
    }
    return ans
}