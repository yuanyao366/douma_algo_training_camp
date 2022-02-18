class Solution {
private:
    vector<vector<int>> dirs = {{1, 0}, {0, 1}};

public:
    // 3. 记忆化搜索
    int minPathSum1(vector<vector<int>>& grid) {
        vector<vector<int>> memo(grid.size(), vector<int>(grid[0].size(), INT_MAX));
        return dfs(grid, 0, 0, memo);
    }

    int dfs(vector<vector<int>>& grid, int row, int col, vector<vector<int>>& memo) {
        if (row == grid.size() - 1 && col == grid[0].size() - 1) {
            return grid[row][col];
        }

        if (memo[row][col] != INT_MAX) return memo[row][col];

        int minPathSum = INT_MAX;
        for (vector<int> dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < 0 || nextCol < 0
                    || nextRow >= grid.size()
                    || nextCol >= grid[0].size()) continue;
            int childMinPathSum = dfs(grid, nextRow, nextCol, memo);
            minPathSum = min(minPathSum, childMinPathSum);
        }
        memo[row][col] = minPathSum + grid[row][col];
        return memo[row][col];
    }

    // 4. 动态规划：从终点到起始点
    int minPathSum2(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();

        // 状态定义：dp[i][j] 表示从坐标 (i, j) 到右下角的最小路径和
        vector<vector<int>> dp(m, vector<int>(n));

        // 状态初始化
        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        // 状态转移
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) { // 最后一行
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (i != m - 1 && j == n - 1) { // 最后一列
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (i != m - 1 && j != n - 1) {
                    dp[i][j] = grid[i][j] + min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // 返回结果
        return dp[0][0];
    }

    // 5. 动态规划：从起始点到终点
    int minPathSum3(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();

        // 状态定义：dp[i][j] 表示从 [0,0] 到 [i,j] 的最小路径和
        vector<vector<int>> dp(m, vector<int>(n));

        // 状态初始化
        dp[0][0] = grid[0][0];

        // 状态转移
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) { //第一行
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (i != 0 && j == 0) { // 第一列
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else if (i != 0 && j != 0) {
                    dp[i][j] = grid[i][j] + min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回结果
        return dp[m - 1][n - 1];
    }

    // 6. 动态规划：从起始点到终点 + 状态压缩
    int minPathSum4(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();

        // 状态定义：dp[i] 表示从 (0, 0) 到达第 i - 1 行的最短路径值
        vector<int> dp(n);

        // 状态初始化
        dp[0] = grid[0][0];

        // 状态转移
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) { //第一行
                    dp[j] = grid[i][j] + dp[j - 1];
                } else if (i != 0 && j == 0) { // 第一列
                    dp[j] = grid[i][j] + dp[j];
                } else if (i != 0 && j != 0) {
                    dp[j] = grid[i][j] + min(dp[j], dp[j - 1]);
                }
            }
        }

        // 返回结果
        return dp[n - 1];
    }

    // 7. 动态规划：从起始点到终点 + 使用输入数组作为状态数组
    int minPathSum(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();

        // 状态转移
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j != 0) { //第一行
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (i != 0 && j == 0) { // 第一列
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else if (i != 0 && j != 0) {
                    grid[i][j] = grid[i][j] + min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }

        // 返回结果
        return grid[m - 1][n - 1];
    }
};