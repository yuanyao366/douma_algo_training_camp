/**
 * @param {number[]} nums
 * @return {number}
 */
// 动态规划 (前缀和解法)
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)
var maxSubArray1 = function(nums) {
    // 状态定义：prefixSum[i] 表示子数组 [0, j] 的累加和
    const prefixSum = new Array(nums.length).fill(0)
    // 状态初始化
    prefixSum[0] = nums[0];
    let maxSum = prefixSum[0];
    for (let i = 1; i < nums.length; i++) {
        prefixSum[i] = prefixSum[i - 1] + nums[i];
        maxSum = Math.max(maxSum, prefixSum[i]);
    }
    // 状态转移
    for (let i = 1; i < nums.length; i++) {
        for (let j = i; j < nums.length; j++) {
            // [i, j]
            const sum = prefixSum[j] - prefixSum[i - 1];
            maxSum = Math.max(maxSum, sum);
        }
    }
    return maxSum;
};

// 动态规划(改变状态定义)
// 时间复杂度：O(n)
// 空间复杂度：O(n)
var maxSubArray2 = function(nums) {
    // 状态定义：dp[i] 表示以索引为 i 的元素结尾的最大子数组和
    const dp = new Array(nums.length).fill(0)

    // 状态初始化
    dp[0] = nums[0];
    let maxSum = dp[0];
    // 状态转移
    for (let i = 1; i < nums.length; i++) {
        dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        maxSum = Math.max(maxSum, dp[i]);
    }
    return maxSum;
};

// 动态规划(改变状态定义) + 状态压缩
// 时间复杂度：O(n)
// 空间复杂度：O(n)
var maxSubArray = function(nums) {
    let preMaxSum = nums[0];
    let maxSum = preMaxSum;
    for (let i = 1; i < nums.length; i++) {
        preMaxSum = Math.max(preMaxSum + nums[i], nums[i]);
        maxSum = Math.max(maxSum, preMaxSum);
    }
    return maxSum;
};
