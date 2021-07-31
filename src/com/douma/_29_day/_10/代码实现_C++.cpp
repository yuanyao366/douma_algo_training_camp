class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.length();
        int n = p.length();

        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1, false));

        // 1. 空字符串和空字符串是匹配的
        dp[0][0] = true;
        // 2. 空字符串和任何单个字符都不匹配（代码可省略）
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }
        // 3. 前前一个元素匹配空字符串并且当前字符是 * ，那么是匹配
        for (int i = 1; i <= n; i++) {
            if ((i >= 2 && dp[0][i - 2]) && p[i - 1] == '*') {
                dp[0][i] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    if (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }

        return dp[m][n];
    }
};