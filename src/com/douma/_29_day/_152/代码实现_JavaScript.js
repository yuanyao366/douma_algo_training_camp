/**
 * @param {number[]} nums
 * @return {number}
 */
// 动态规划
var maxProduct1 = function(nums) {
    const len = nums.length
    const maxP = new Array(len).fill(0)
    const minP = new Array(len).fill(0)
    maxP[0] = minP[0] = nums[0]
    let ans = nums[0]

    for (let i = 1; i < len; i++) {
        maxP[i] = Math.max(maxP[i - 1] * nums[i], Math.max(minP[i - 1] * nums[i], nums[i]))
        minP[i] = Math.min(minP[i - 1] * nums[i], Math.min(maxP[i - 1] * nums[i], nums[i]))
        ans = Math.max(ans, maxP[i])
    }

    return ans
};

// 动态规划 + 状态压缩
var maxProduct = function(nums) {
    const len = nums.length
    let maxP = minP = nums[0]
    let ans = nums[0]

    for (let i = 1; i < len; i++) {
        const mx = maxP, mn = minP
        maxP = Math.max(mx * nums[i], Math.max(mn * nums[i], nums[i]))
        minP = Math.min(mn * nums[i], Math.min(mx * nums[i], nums[i]))
        ans = Math.max(ans, maxP)
    }

    return ans
};