class Solution {
public:
    // 1. 记忆化搜索（一）
    int rob1(vector<int>& nums) {
        vector<int> memo(nums.size(), -1);
        return dfs(nums, 0, memo);
    }

    int dfs(vector<int> nums, int i, vector<int>& memo) {
        if (i >= nums.size()) return 0;

        if (memo[i] != -1) return memo[i];

        int left = dfs(nums, i + 1, memo);
        int right = dfs(nums, i + 2, memo);

        memo[i] = max(left, right + nums[i]);
        return memo[i];
    }

    // 2. 记忆化搜索（二）
    int rob(vector<int>& nums) {
        vector<int> memo(nums.size(), -1);
        return dfs2(nums, nums.size() - 1, memo);
    }

    int dfs2(vector<int> nums, int i, vector<int>& memo) {
        if (i == 0) return nums[0];
        if (i == 1) return max(nums[0], nums[1]);

        if (memo[i] != -1) return memo[i];

        int left = dfs2(nums, i - 1, memo);
        int right = dfs2(nums, i - 2, memo);

        memo[i] = max(left, right + nums[i]);
        return memo[i];
    }

    // 3. 动态规划
    int rob3(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];

        vector<int> dp(n, -1);

        dp[0] = nums[0];
        dp[1] = max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

     // 4. 动态规划 + 状态压缩
    int rob4(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];

        int preMax = 0;
        int currMax = 0;

        for (int num : nums) {
            int tmpMax = max(currMax, preMax + num);
            preMax = currMax;
            currMax = tmpMax;
        }

        return currMax;
    }
};