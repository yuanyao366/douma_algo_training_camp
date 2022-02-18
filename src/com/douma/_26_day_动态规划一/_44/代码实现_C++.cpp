class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.length(), n = p.length();

        // 状态定义：dp[i][j] 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1, false));

        // 状态初始化
        // 1. 空字符串和空字符串是匹配的
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            // 3. 空字符串和 * 是匹配的
            if (dp[0][i - 1] && p[i - 1] == '*') {
                dp[0][i] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s[i - 1] == p[j - 1]
                        || p[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(p[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }
};