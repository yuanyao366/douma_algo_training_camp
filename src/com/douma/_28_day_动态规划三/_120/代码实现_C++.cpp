class Solution {
public:
    int minimumTotal1(vector<vector<int>>& triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 [i, j] 到底边的最小路径和。
        vector<vector<int>> dp(n, vector<int>(n));

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = triangle[n - 1][i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
            }
        }
        return dp[0][0];
    }

    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 [i, j] 到底边的最小路径和。
        vector<int> dp(n);

        for (int i = 0; i < n; i++) {
            dp[i] = triangle[n - 1][i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j];
            }
        }
        return dp[0];
    }
};