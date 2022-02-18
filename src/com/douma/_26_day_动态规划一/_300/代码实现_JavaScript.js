/**
 * @param {number[]} nums
 * @return {number}
 */
var lengthOfLIS = function(nums) {
    const m = nums.length
    if (m == 0) return 0

    const dp = new Array(m).fill(1)

    let res = 1
    for (let j = 1; j < m; j++) {
        for (let i = 0; i < j; i++) {
            if (nums[j] > nums[i]) {
                dp[j] = Math.max(dp[i] + 1, dp[j])
                res = Math.max(res, dp[j])
            }
        }
    }

    return res
};