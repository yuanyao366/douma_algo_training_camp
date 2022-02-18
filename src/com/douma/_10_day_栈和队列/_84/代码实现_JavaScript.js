/**
 * @param {number[]} heights
 * @return {number}
 */
 // 枚举高 + 单调栈优化 + 一次遍历
var largestRectangleArea = function(heights) {
    n = heights.length

    const left = new Array(n).fill(0)
    const right = new Array(n).fill(n)
    const stack = []
    for (let i = 0; i < n; i++) {
        while (stack.length && heights[i] <= heights[stack[stack.length - 1]]) {
            right[stack[stack.length - 1]] = i
            stack.pop()
        }
        left[i] = (!stack.length ? -1 : stack[stack.length - 1])
        stack.push(i)
    }

    let ans = 0
    for (let mid = 0; mid < n; mid++) {
        const height = heights[mid]
        const width = right[mid] - left[mid] - 1
        ans = Math.max(ans, height * width)
    }

    return ans
};