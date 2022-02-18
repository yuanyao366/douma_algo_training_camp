class Solution {
public:
    int splitArray(vector<int>& nums, int m) {
        int n = nums.size();
        vector<int> prefixSum(n + 1);
        for (int i = 1; i <= n; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i - 1];
        }

        // dp[i][j]: 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值
        vector<vector<int>> dp(n + 1, vector<int>(m + 1, INT_MAX));

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m && j <= i; j++) {
                for (int l = 0; l < i; l++) {
                    dp[i][j] = min(dp[i][j], max(dp[l][j - 1], prefixSum[i] - prefixSum[l]));
                }
            }
        }

        return dp[n][m];
    }
};