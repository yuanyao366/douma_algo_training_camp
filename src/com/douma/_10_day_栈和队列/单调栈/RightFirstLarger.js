var findRightSmall = function(nums) {
    const ans = new Array(nums.length).fill(-1)
    const stack = []
    for (let i = 0; i < nums.length; i++) {
        const x = nums[i]
        // 单调递减栈
        while (stack.length && x > nums[stack[stack.length - 1]]) {
            ans[stack[stack.length - 1]] = i
            stack.pop()
        }
        stack.push(i)
    }
    return ans
}