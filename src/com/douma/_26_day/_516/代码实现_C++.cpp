class Solution {
public:
    // 动态规划(一)
    int longestPalindromeSubseq(string s) {
        if (s.length() == 0) return 0;

        int m = s.length();
        // 状态：dp[i][j] 表示在区间 [i...j] 中最长回文子序列的长度
        vector<vector<int>> dp(m, vector<int>(m));

        // 初始化，一个字符的最长回文子序列长度是 1
        for (int i = 0; i < m; i++) {
            dp[i][i] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = i + 1; j < m; j++) {
                if (s[i] == s[j]) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][m - 1];
    }
};