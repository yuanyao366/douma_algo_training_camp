/**
 * @param {number[]} nums
 * @return {number}
 */
// 贪心策略：每步都选择能跳到的最远距离
var jump = function(nums) {
    if (nums.length == 1) return 0

    let steps = 0, start = 0, end = 0, maxPos = 0
    // 走到最后一个位置的时候就不用走了
    while (end < nums.length - 1) {
        // 每次从 [start, end] 中都选择能跳到的最远距离
        for (let i = start; i <= end; i++) {
            maxPos = Math.max(maxPos, i + nums[i])
        }
        start = end + 1
        end = maxPos
        steps++
    }

    return steps
};