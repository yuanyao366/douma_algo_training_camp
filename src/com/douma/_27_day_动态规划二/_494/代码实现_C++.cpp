class Solution {
public:
    // 0 - 1 背包问题
    // 假设数组中所有数字的总和为 sum
    // 假设前面设置为负数的数字的总和是 neg。那么设置为正数的数字的总和为 sum - neg
    // 那么 (sum - neg) - neg = target => neg = （sum - target）/ 2
    // 所以问题转为 0-1 背包问题：
    // 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg
    // 求有多少组合数？
    int findTargetSumWays1(vector<int>& nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        int diff = sum - target;
        if (diff < 0 || diff % 2 == 1) return 0;

        int neg = diff / 2;
        vector<int> dp(neg + 1);
        dp[0] = 1;
        for (int num : nums) {
            for (int c = neg; c >= num; c--) {
                dp[c] = dp[c] + dp[c - num];
            }
        }

        return dp[neg];
    }

    // DFS
    int ans = 0;
    int findTargetSumWays(vector<int>& nums, int target) {
        dfs(nums, target, 0, 0);
        return ans;
    }

    void dfs(vector<int>& nums, int target, int i, int sum) {
        if (i == nums.size()) {
            if (sum == target) ans++;
            return;
        }

        dfs(nums, target, i + 1, sum + nums[i]);
        dfs(nums, target, i + 1, sum - nums[i]);
    }
};