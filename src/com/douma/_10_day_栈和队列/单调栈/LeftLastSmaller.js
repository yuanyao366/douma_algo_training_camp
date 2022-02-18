var findRightSmall = function(nums) {
    const ans = new Array(nums.length).fill(-1)
    const stack = []
    // 从右往左遍历
    for (let i = nums.length - 1; i >= 0; i--) {
        const x = nums[i]
        // 单调递增栈
        while (stack.length && x < nums[stack[stack.length - 1]]) {
            ans[stack[stack.length - 1]] = i
            stack.pop()
        }
        stack.push(i)
    }
    return ans
}