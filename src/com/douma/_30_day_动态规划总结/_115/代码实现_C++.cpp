class Solution {
public:
    int numDistinct(string s, string t) {
        int m = s.length(), n = t.length();
        if (m < n) return 0;

        // dp[i][j]：表示 s 的前 i 个字符中组成 t 的前 j 个字符的最多个数
        // 使用 double ，防止 dp 中间状态值溢出
        vector<vector<double>> dp(m + 1, vector<double>(n + 1, 0));

        for (int i = 0; i <= m; i++) {
            // s 的前 i 个字符中始终包含一个 空字符串
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s[i - 1] == t[j - 1]) {
                    /*
                    s[i] == t[j]的时候, s[i] 可以选择自己是否跟 t[j]匹配
                        如果匹配，那么 dp[i][j] 其中一部分数量就是 dp[i-1][j-1]
                        如果选择不匹配（这样可以让前面的字符跟t[j]匹配，毕竟 t 短的,s 长) dp[i][j] 另外一部分就是 dp[i - 1][j]
                     */
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
};