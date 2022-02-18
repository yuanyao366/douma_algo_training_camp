class Solution {
public:
    // 动态规划 (前缀和解法)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n)
    int maxSubArray1(vector<int>& nums) {
        // 状态定义：prefixSum[i] 表示子数组 [0, j] 的累加和
        vector<int> prefixSum(nums.size());

        // 状态初始化
        prefixSum[0] = nums[0];
        int maxSum = prefixSum[0];
        for (int i = 1; i < nums.size(); i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            maxSum = max(maxSum, prefixSum[i]);
        }

        // 状态转移
        for (int i = 1; i < nums.size(); i++) {
            for (int j = i; j < nums.size(); j++) {
                // [i, j]
                int sum = prefixSum[j] - prefixSum[i - 1];
                maxSum = max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // 动态规划(改变状态定义)
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    int maxSubArray2(vector<int>& nums) {
        // 状态定义：dp[i] 表示以索引为 i 的元素结尾的最大子数组和
        vector<int> dp(nums.size());

        // 状态初始化
        dp[0] = nums[0];
        int maxSum = dp[0];

        // 状态转移
        for (int i = 1; i < nums.size(); i++) {
            dp[i] = max(dp[i - 1] + nums[i], nums[i]);
            maxSum = max(maxSum, dp[i]);
        }

        return maxSum;
    }

    // 动态规划(改变状态定义) + 状态压缩
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    int maxSubArray(vector<int>& nums) {
        // 状态初始化
        int preMaxSum = nums[0];
        int maxSum = preMaxSum;

        // 状态转移
        for (int i = 1; i < nums.size(); i++) {
            preMaxSum = max(preMaxSum + nums[i], nums[i]);
            maxSum = max(maxSum, preMaxSum);
        }

        return maxSum;
    }

};