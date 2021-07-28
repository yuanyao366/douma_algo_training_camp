class Solution {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        int ans = 0;

        // 初始化状态数组
        // 行的长度和列的长度都增加 1，有利于边界条件的处理
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = min(dp[i - 1][j], min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    ans = max(ans, dp[i][j]);
                }
            }
        }

        return ans * ans;
    }
};