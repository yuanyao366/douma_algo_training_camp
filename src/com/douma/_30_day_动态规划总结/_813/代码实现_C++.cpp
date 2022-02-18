class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        vector<double> prefixSum(n + 1);
        for (int i = 1; i <= n; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i - 1];
        }

        // dp[i][j]: 表示将 nums 的前 i 个元素分割成 j 份得到的最大平均分数
        vector<vector<double>> dp(n + 1, vector<double>(k + 1));

        for (int i = 1; i <= n; i++) {
            // 将 nums 的前 i 个元素分割成 1 份得到的最大分数
            dp[i][1] = prefixSum[i] / i;

            for (int j = 2; j <= k && j <= i; j++) {
                // 将 nums 的前 i 个元素分割成 j 份得到的最大平均分数等于：
                // 所有将前 l 个字符分割成 j - 1 份得到的最大平均分数，再加上 [l...i] 的平均值，求最大值
                for (int l = 1; l < i; l++) {
                    dp[i][j] = max(dp[i][j], dp[l][j - 1] + (prefixSum[i] - prefixSum[l]) / (i - l));
                }
            }
        }

        return dp[n][k];
    }
};