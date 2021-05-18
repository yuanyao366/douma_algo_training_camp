/**
 * @param {number[]} height
 * @return {number}
 */
// 预计算最大值
var trap1 = function(height) {
    const n = height.length
    if (n <= 2) return 0

    leftMax = new Array(n).fill(0)
    leftMax[0] = height[0]
    for (let i = 1; i < n; i++) {
        leftMax[i] = Math.max(leftMax[i - 1], height[i])
    }

    rightMax = new Array(n).fill(0)
    rightMax[n - 1] = height[n - 1]
    for (let i = n - 2; i >= 0; i--) {
        rightMax[i] = Math.max(rightMax[i + 1], height[i])
    }

    let ans = 0
    for (let i = 1; i < n - 1; i++) {
        const maxHeight = Math.min(leftMax[i], rightMax[i])
        if (maxHeight > height[i]) ans += maxHeight - height[i]
    }

    return ans
};

// 双指针
var trap2 = function(height) {
    const n = height.length
    if (n <= 2) return 0

    let leftMax = 0, rightMax = 0
    let left = 0, right = n - 1
    let ans = 0
    while (left < right) {
        leftMax = Math.max(leftMax, height[left])
        rightMax = Math.max(rightMax, height[right])
        if (height[left] < height[right]) {
            ans += leftMax - height[left]
            left++
        } else {
            ans += rightMax - height[right]
            right--
        }
    }
    return ans
}

// 单调栈
var trap = function(height) {
    const n = height.length
    if (n <= 2) return 0

    let ans = 0
    const stack = []
    for (let i = 0; i < n; i++) {
        while (stack.length && height[i] > height[stack[stack.length - 1]]) {
            const top = stack[stack.length - 1]
            stack.pop()
            if (!stack.length) break
            const leftIndex = stack[stack.length - 1]
            const currWidth = i - leftIndex - 1
            const currHeight = Math.min(height[leftIndex], height[i]) - height[top]
            ans += currHeight * currWidth
        }
        stack.push(i)
    }

    return ans
}