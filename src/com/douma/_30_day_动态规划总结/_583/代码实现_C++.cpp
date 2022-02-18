class Solution {
public:
    /*
    为了求得最少删除次数，我们可以求出串 word1 和串 word2 最长公共子序列，我们记为 lcs。
    如果我们能求得 lcs 的值，我们可以轻易地求出答案，为 m + n - 2 * lcs。
    这里 m 和 n 分别是给定字符串 word1 和 word2 的长度。

    问题转化为 LCS 问题
     */
    int minDistance(string word1, string word2) {
        int m = word1.length(), n = word2.length();

        // dp[i][j] word1 前 i 个字符和 word2 前 j 个字符【最长公共子序列长度】
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return m + n - 2 * dp[m][n];
    }
};