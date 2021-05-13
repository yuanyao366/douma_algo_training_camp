/**
 * @param {number[]} nums
 * @return {number}
 */
var firstMissingPositive = function(nums) {
    const n = nums.length
    for (let i = 0; i < n; i++) {
        if (nums[i] <= 0) nums[i] = n + 1
    }
    for (let i = 0; i < n; i++) {
        const num = Math.abs(nums[i])
        if (num <= n)
            nums[num - 1] = -Math.abs(nums[num - 1]);
    }
    for (let i = 0; i < n; i++) {
        if (nums[i] > 0) return i + 1;
    }
    return n + 1;
};