/**
 * @param {number[]} nums
 * @return {boolean}
 */
//1. 单变量维护最小值 + 线性查找 k
// 时间复杂度：O(n^2)
var find132pattern1 = function(nums) {
    const n = nums.length
    if (n < 3) return false

    let numsi = nums[0]
    for (let j = 1; j < n; j++) {
        for (let k = j + 1; k < n; k++) {
            if (numsi < nums[k] && nums[k] < nums[j]) {
                return true
            }
        }
        numsi = Math.min(numsi, nums[j])
    }

    return false;
};

//3. 预计算前缀最小值 + 单调栈
// 时间复杂度：O(n)
var find132pattern3 = function(nums) {
    const n = nums.length
    if (n < 3) return false

    const minPrefix = new Array(n).fill(0)
    minPrefix[0] = nums[0]
    for (let i = 1; i < n; i++) {
        minPrefix[i] = Math.min(minPrefix[i - 1], nums[i])
    }

    const stack = []
    stack.push(nums[n - 1])
    for (let j = n - 2; j >= 1; j--) {
        while (stack.length && stack[stack.length - 1] <= minPrefix[j]) {
            stack.pop()
        }
        if (stack.length && stack[stack.length - 1] < nums[j]) {
            return true
        }
        stack.push(nums[j])
    }
    return false;
};

//4. 单调栈
// 时间复杂度：O(n)
var find132pattern = function(nums) {
    const n = nums.length
    if (n < 3) return false

    let maxk = -Number.MAX_SAFE_INTEGER;

    const stack = []
    stack.push(nums[n - 1])
    for (let i = n - 2; i >= 0; i--) {
        if (nums[i] < maxk) return true
        while (stack.length && nums[i] > stack[stack.length - 1]) {
            maxk = stack[stack.length - 1]
            stack.pop()
        }
        if (nums[i] > maxk) stack.push(nums[i])
    }
    return false;
};