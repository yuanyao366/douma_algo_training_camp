/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
 // 0 - 1 背包问题
// 假设数组中所有数字的总和为 sum
// 假设前面设置为负数的数字的总和是 neg。那么设置为正数的数字的总和为 sum - neg
// 那么 (sum - neg) - neg = target => neg = （sum - target）/ 2
// 所以问题转为 0-1 背包问题：
// 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg
// 求有多少组合数？
var findTargetSumWays1 = function(nums, target) {
    let sum = 0
    for (const num of nums) sum += num

    const diff = sum - target
    if (diff < 0 || diff % 2 == 1) return 0

    const neg = diff / 2
    const dp = new Array(neg + 1).fill(0)
    dp[0] = 1

    for (const num of nums) {
        for (let c = neg; c >= num; c--) {
            dp[c] += dp[c - num]
        }
    }

    return dp[neg]
};

// DFS
var findTargetSumWays = function(nums, target) {
    let ans = 0

    const dfs = (i, sum) => {
        if (i == nums.length) {
            if (sum == target) ans++
            return
        }

        dfs(i + 1, sum + nums[i])
        dfs(i + 1, sum - nums[i])
    }

    dfs(0, 0)
    return ans
}