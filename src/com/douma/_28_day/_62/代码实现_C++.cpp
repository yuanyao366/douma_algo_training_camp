class Solution {
public:
    // 1. 记忆化搜索
    int uniquePaths1(int m, int n) {
        vector<vector<int>> memo(m, vector<int>(n, -1));
        return dfs(m, n, 0, 0, memo);
    }

    int dfs(int m, int n, int i, int j, vector<vector<int>>& memo) {
        if (i == m - 1 && j == n - 1) return 1;
        if (i == m || j == n) return 0;

        if (memo[i][j] != -1) return memo[i][j];

        int left = dfs(m, n, i, j + 1, memo);
        int right = dfs(m, n, i + 1, j, memo);

        memo[i][j] = left + right;
        return memo[i][j];
    }

    // 2. 动态规划(左上到右下)
    int uniquePaths2(int m, int n) {
        // dp[i][j]：表示从位置 [i, j] 到 [m - 1. n - 1] 的路径数
        vector<vector<int>> dp(m, vector<int>(n));

        // 状态转移
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    // 3. 动态规划(左上到右下) - 状态压缩
    int uniquePaths3(int m, int n) {
        // dp[i][j]：表示从位置 [i, j] 到 [m - 1. n - 1] 的路径数
        vector<int> dp(n);

        // 状态转移
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j] + dp[j + 1];
                }
            }
        }
        return dp[0];
    }

     // 4. 动态规划(右下到左上)
    int uniquePaths4(int m, int n) {
        // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
        vector<vector<int>> dp(m, vector<int>(n));

        // 状态转移
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

     // 5. 动态规划(右下到左上)- 状态压缩
    int uniquePaths(int m, int n) {
        // dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
        vector<int> dp(n);

        // 状态转移
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else if (i != 0 && j != 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return dp[n - 1];
    }
};