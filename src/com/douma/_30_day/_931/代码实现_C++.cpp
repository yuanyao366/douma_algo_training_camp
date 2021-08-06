class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        int n = matrix.size();

        // dp[i][j]：表示元素 [i, j] 的下降路径最小和
        vector<vector<int>> dp(n, vector<int>(n, 0));

        // 最后一行所有元素的下降路径最小和就是元素值本身
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = matrix[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // dp[i][j] = min(dp[i + 1][j], dp[i + 1][j - 1], dp[i + 1][j + 1])
                int minSum = dp[i + 1][j];
                if (j > 0) minSum = min(minSum, dp[i + 1][j - 1]);
                if (j + 1 < n) minSum = min(minSum, dp[i + 1][j + 1]);

                dp[i][j] = minSum + matrix[i][j];
            }
        }

        int ans = INT_MAX;
        for (int x : dp[0]) ans = min(ans, x);

        return ans;
    }
};