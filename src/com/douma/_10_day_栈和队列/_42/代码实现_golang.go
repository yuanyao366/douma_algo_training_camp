// 方法一
// 时间复杂度：O(n^2)
// 空间复杂度：O(1)
func trap1(height []int) int {
    if len(height) <= 2 {
        return 0
    }

    var ans = 0
    for i := 1; i < len(height); i++ {
        // 求左边的最大值
        var leftMax = 0
        for j := i - 1; j >= 0; j-- {
            if height[j] > leftMax {
                leftMax = height[j]
            }
        }

        // 求右边的最大值
        var rightMax = 0
        for j := i + 1; j < len(height); j++ {
            if height[j] > rightMax {
                rightMax = height[j]
            }
        }

        // 当前这个柱子能装的水的单位数等于 min(leftMax, rightMax) - height[i]
        var maxHeight = leftMax
        if rightMax < leftMax {
            maxHeight = rightMax
        }
        if maxHeight > height[i] {
            ans += maxHeight - height[i]
        }
    }

    return ans
}

// 方法二：空间换时间
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func trap2(height []int) int {
    if len(height) <= 2 {
        return 0
    }

    var n = len(height)

    var leftMax = make([]int, n)
    leftMax[0] = height[0]
    for i := 1; i < n; i++ {
        leftMax[i] = max(leftMax[i - 1], height[i])
    }

    var rightMax = make([]int, n)
    rightMax[n - 1] = height[n - 1]
    for i := n - 2; i >= 0; i-- {
        rightMax[i] = max(rightMax[i + 1], height[i])
    }

    var ans = 0
    for i := 1; i < n; i++ {
        // 当前这个柱子能装的水的单位数等于 min(leftMax, rightMax) - height[i]
        var maxHeight = leftMax[i]
        if rightMax[i] < leftMax[i] {
            maxHeight = rightMax[i]
        }
        if maxHeight > height[i] {
            ans += maxHeight - height[i]
        }
    }

    return ans
}

// 方法三：双指针优化，降低空间复杂度
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func trap3(height []int) int {
    if len(height) <= 2 {
        return 0
    }

    var n = len(height)

    var leftMax, rightMax = 0, 0
    // 注意：left 必须从 0 开始，right 必须从 n - 1 开始
    // 原因：第一根柱子或者最后一根柱子有可能是最大值
    var left, right = 0, n - 1
    var ans = 0
    for left < right {
        leftMax = max(leftMax, height[left])
        rightMax = max(rightMax, height[right])
        if height[left] < height[right] {
            ans += leftMax - height[left]
            left++
        } else {
            ans += rightMax - height[right]
            right--
        }
    }

    return ans
}


// 方法四：单调栈优化
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func trap(height []int) int {
    if len(height) <= 2 {
        return 0
    }

    var n = len(height)

    var stack = []int{}
    var ans = 0
    for i := 0; i < n; i++ {
        for len(stack) > 0 && height[i] > height[stack[len(stack) - 1]] {
            var top = stack[len(stack) - 1]
            stack = stack[:len(stack) - 1]
            if len(stack) == 0 {
                break
            }

            var leftIndex = stack[len(stack) - 1]
            var currWidth = i - leftIndex - 1
            var currHeight = min(height[leftIndex], height[i]) - height[top]
            ans += currWidth * currHeight
        }
        stack = append(stack, i)
    }

    return ans
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}
