/**
 * @param {number[]} nums
 * @param {number} m
 * @return {number}
 */
var splitArray = function(nums, m) {
    const n = nums.length
    const prefixSum = new Array(n + 1).fill(0.0)
    for (let i = 1; i <= n; i++) {
        prefixSum[i] += prefixSum[i - 1] + nums[i - 1]
    }

    // dp[i][j]: 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值
    const dp = new Array(n + 1).fill(0).map(() => new Array(m + 1).fill(Math.pow(2, 31) - 1))

    dp[0][0] = 0;
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= m && j <= i; j++) {
            for (let l = 0; l < i; l++) {
                dp[i][j] = Math.min(dp[i][j], Math.max(dp[l][j - 1], prefixSum[i] - prefixSum[l]))
            }
        }
    }

    return dp[n][m]
};