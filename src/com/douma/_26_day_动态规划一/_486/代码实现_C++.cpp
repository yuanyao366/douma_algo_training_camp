class Solution {
public:
    // 记忆化搜索
    bool PredictTheWinner1(vector<int>& nums) {
        int m = nums.size();
        vector<vector<int>> memo(m, vector<int>(m, INT_MIN));
        return dfs(nums, 0, m - 1, memo) >= 0;
    }

    int dfs(vector<int>& nums, int i, int j, vector<vector<int>>& memo) {
        if (i == j) return nums[i];

        if (memo[i][j] != INT_MIN) return memo[i][j];

        int pickI = nums[i] - dfs(nums, i + 1, j, memo);
        int pickJ = nums[j] - dfs(nums, i, j - 1, memo);

        memo[i][j] = max(pickI, pickJ);
        return memo[i][j];
    }

    // 动态规划
    bool PredictTheWinner(vector<int>& nums) {
        int m = nums.size();
        // dp[i][j] 表示玩家 1 在区间 [i, j] 之内可以赢的最多的分
        vector<vector<int>> dp(m, vector<int>(m, INT_MIN));

        for (int i = 0; i < m; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = i + 1; j < m; j++) {
                dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][m - 1] >= 0;
    }
};