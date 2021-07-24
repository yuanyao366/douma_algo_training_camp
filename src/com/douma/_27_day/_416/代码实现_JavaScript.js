/**
 * @param {number[]} nums
 * @return {boolean}
 */
// 先计算得到数组的总和为 sum，然后将 sum / 2 得到一半，记为 target
// 问题转变为 0-1 背包问题：在数组 nums 中不可重复的选择数字组合，是否存在和等于 target 的组合呢？
var canPartition = function(nums) {
    let sum = 0;
    for (const num of nums) sum += num
    if (sum % 2 == 1) return false

    const target = Math.floor(sum / 2)

    // dp[c] : 表示从 nums 中是否可以找到总和等于 c 的元素组合
    const dp = new Array(target + 1).fill(false)
    dp[0] = true

    for (const num of nums) {
        for (let c = target; c >= num; c--) {
            dp[c] = dp[c] || dp[c - num]
        }
    }

    return dp[target]
};