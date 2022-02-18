class Solution {
public:
    // 两个字符串的最小 ASCII 删除和等于两个字符串所有字母的和减去两个字符串最大公共子序列所有字母的和
    // 问题转化成 LCS 问题
    int minimumDeleteSum(string s1, string s2) {
        int m = s1.length();
        int n = s2.length();

        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += s1[i];
        }
        for (int i = 0; i < n; i++) {
            sum += s2[i];
        }

        // dp[i][j]：s1 前 i 个字符和 s2 前 j 个字符【最长公共子序列的所有字母和】
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + s1[i - 1];
                } else {
                    dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return sum - 2 * dp[m][n];
    }
};