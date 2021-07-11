/**
 * @param {number[]} nums
 * @return {boolean}
 */
// 贪心策略：每步都选择能跳到的最远距离
var canJump = function(nums) {
    let maxPos = 0
    for (let i = 0; i < nums.length; i++) {
        if (i > maxPos) return false;
        maxPos = Math.max(maxPos, i + nums[i])
    }
    return true
};