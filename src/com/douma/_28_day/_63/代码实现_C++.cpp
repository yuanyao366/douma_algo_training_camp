class Solution {
public:
    int uniquePathsWithObstacles1(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size();
        int n = obstacleGrid[0].size();

        // 1. 状态定义
        // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        vector<vector<int>> dp(m, vector<int>(n));

        // 状态转移
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size();
        int n = obstacleGrid[0].size();

        // 1. 状态定义
        // dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        vector<int> dp(n);

        // 状态转移
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[j] = 1;
                } else if (i == 0) {
                    dp[j] = dp[j - 1];
                } else if (j == 0) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
};