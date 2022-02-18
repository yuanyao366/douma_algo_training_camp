/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var largestSumOfAverages = function(nums, k) {
    const n = nums.length
    const prefixSum = new Array(n + 1).fill(0.0)
    for (let i = 1; i <= n; i++) {
        prefixSum[i] += prefixSum[i - 1] + nums[i - 1]
    }

    // dp[i][j]: 表示将 nums 的前 i 个元素分割成 j 份得到的最大平均分数
    const dp = new Array(n + 1).fill(0.0).map(() => new Array(k + 1).fill(0.0))

    for (let i = 1; i <= n; i++) {
        // 将 nums 的前 i 个元素分割成 1 份得到的最大分数
        dp[i][1] = prefixSum[i] / i

        for (let j = 2; j <= k && j <= i; j++) {
            // 将 nums 的前 i 个元素分割成 j 份得到的最大平均分数等于：
            // 所有将前 l 个字符分割成 j - 1 份得到的最大平均分数，再加上 [l...i] 的平均值，求最大值
            for (let l = 1; l < i; l++) {
                dp[i][j] = Math.max(dp[i][j], dp[l][j - 1] + (prefixSum[i] - prefixSum[l]) / (i - l))
            }
        }
    }

    return dp[n][k]
};