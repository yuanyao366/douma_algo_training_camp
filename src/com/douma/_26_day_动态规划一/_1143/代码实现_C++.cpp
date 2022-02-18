class Solution {
public:
    int longestCommonSubsequence1(string text1, string text2) {
        int m = text1.length(), n = text2.length();

        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1[i - 1] == text2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }

    // 状态压缩
    int longestCommonSubsequence2(string text1, string text2) {
        int m = text1.length(), n = text2.length();

        // 调换字符串顺序可以减少空间的使用
        if (m < n) {
            return longestCommonSubsequence2(text2, text1);
        }

        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
        // 当前的状态依赖于前一行和当前行两行的状态值
        vector<vector<int>> dp(2, vector<int>(n + 1));

        for (int i = 1; i <= m; i++) {
            int currRow = i % 2;
            for (int j = 1; j <= n; j++) {
                if (text1[i - 1] == text2[j - 1]) {
                    dp[currRow][j] = 1 + dp[1 - currRow][j - 1];
                } else {
                    dp[currRow][j] = max(dp[currRow][j - 1], dp[1 - currRow][j]);
                }
            }
        }

        return dp[m % 2][n];
    }

    // 状态继续压缩
    int longestCommonSubsequence(string text1, string text2) {
        int m = text1.length(), n = text2.length();

        // 调换字符串顺序可以减少空间的使用
        if (m < n) {
            return longestCommonSubsequence(text2, text1);
        }

        // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
        // 当前的状态依赖于前一行和当前行两行的状态值，我们可以使用两个变量来存储
        vector<int> dp(n + 1);

        for (int i = 1; i <= m; i++) {
            int preRow = 0, preRowPreCol = 0;
            for (int j = 1; j <= n; j++) {
                preRowPreCol = preRow;
                preRow = dp[j];
                if (text1[i - 1] == text2[j - 1]) {
                    dp[j] = 1 + preRowPreCol;
                } else {
                    dp[j] = max(preRow, dp[j - 1]);
                }
            }
        }

        return dp[n];
    }
};