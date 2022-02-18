class Solution {
public:
    /*
        数组是环形数组，意味着可以数组的首尾相连，头部和尾巴的元素是连续的
        那么，要求最大子数组和，包括了两种情况：
        1. 假设数组不是环形数组，这个时候只需要按照 leetcode 53 那样在数组内求最大子数组和
        2. 假设数组是环形数组，而且最大子数组和的子数组包含了首尾两个元素
            这种情况，我们只需要求出数组内最小子数组和，然后使用数组累加和减去最小子数组和即可
        最后，染回上面两种情况的最大值
     */
    int maxSubarraySumCircular(vector<int>& nums) {
        // 1. 在原数组 nums 内求最大子数组和，参考 leetcode 53 号算法题：最大子序和
        vector<int> dp(nums.size());
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < nums.size(); i++) {
            dp[i] = max(dp[i - 1] + nums[i], nums[i]);
            maxSum = max(maxSum, dp[i]);
        }

        // 如果最子数组和小于0，说明数组中全为负数，返回最大负数即可
        if (maxSum < 0) return maxSum;

        // 2. 在原数组 nums 内求最小子数组和
        vector<int> dp2(nums.size());
        dp2[0] = nums[0];
        int minSum = dp[0];
        int sum = nums[0]; // 存储 nums 所有元素和
        for (int i = 1; i < nums.size(); i++) {
            dp2[i] = min(dp2[i - 1] + nums[i], nums[i]);
            minSum = min(minSum, dp2[i]);
            sum += nums[i];
        }

        return max(maxSum, sum - minSum);
    }
};