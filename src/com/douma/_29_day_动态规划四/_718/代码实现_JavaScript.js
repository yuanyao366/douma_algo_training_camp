/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
// 动态规划
var findLength = function(nums1, nums2) {
    const m = nums1.length, n = nums2.length

    // dp[i][j]：表示 A 中前 i 个元素中和 B 的前 j 个元素中最长公共子数组长度
    const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0))

    ans = 0
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            dp[i][j] = nums1[i - 1] == nums2[j - 1] ? dp[i - 1][j - 1] + 1 : 0
            ans = Math.max(ans, dp[i][j])
        }
    }

    return ans
};