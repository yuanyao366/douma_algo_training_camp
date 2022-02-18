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
    /*
    说明：这里不就是数组 nums 中不可重复的选择数字组合，是否存在和等于 target 的组合呢？
        我们可以不可以和前面讲过的目标和题目一样，这样定义状态呢：
            dp[c] : 表示从 nums 中找到总和等于 c 的元素组合数
        状态转移方程为：dp[c] = dp[c] + dp[c - nums[i]];
        最后的话，我们只要判断 dp[target] 是否大于 0 即可
    注意：这种方案是不行的，详情请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4TGJ2
     */
    const dp = new Array(target + 1).fill(false)
    dp[0] = true

    for (const num of nums) {
        for (let c = target; c >= num; c--) {
            dp[c] = dp[c] || dp[c - num]
        }
    }

    return dp[target]
};