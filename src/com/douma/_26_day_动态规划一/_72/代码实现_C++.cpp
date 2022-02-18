class Solution {
public:
    int minDistance(string word1, string word2) {
        int m = word1.length(), n = word2.length();
        if (m * n == 0) return m + n;

        // dp[i][j] 表示 word1 前 i 个字符转换成 word2 前 j 个字符花的最少操作数[即编辑距离]
        // vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        int dp[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insertNum = 1 + dp[i][j - 1];
                    int deleteNum = 1 + dp[i - 1][j];
                    int replaceNum = 1 + dp[i - 1][j - 1];
                    dp[i][j] = min(insertNum, min(deleteNum, replaceNum));
                }
            }
        }

        return dp[m][n];
    }
};