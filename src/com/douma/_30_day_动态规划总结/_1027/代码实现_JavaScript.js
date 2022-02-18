/**
 * @param {number[]} nums
 * @return {number}
 */
var longestArithSeqLength = function(nums) {
    const n = nums.length

    let ans = 2
    // dp[i][j]：表示以 nums[i] 为结尾且公差为 j 的最长等差数列的长度
    // 内存溢出，可以使用 Map 来代替数组
    const dp = new Array(n).fill(0).map(() => new Map())

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < i; j++) {
            let diff = nums[i] - nums[j]
            const iDiff = dp[i].has(diff) ? dp[i].get(diff) : 1
            const jDiff = dp[j].has(diff) ? dp[j].get(diff) : 1
            dp[i].set(diff, Math.max(iDiff, jDiff + 1))
            ans = Math.max(dp[i].get(diff), ans)
        }
    }
    return ans
};