class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.length(), n = s2.length(), t = s3.length();

        if (m + n != t) {
            return false;
        }
        // dp[i][j]：s1 的前 i 个字符和 s2 的前 j 个字符是否可以交错组成 s3 的前 i + j 个字符
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));

        dp[0][0] = true;
        // 第一列只能往下走
        for (int i = 1; i <= m; i++) {
            if (s1[i - 1] == s3[i - 1]) {
                dp[i][0] = true;
            } else {
                break; // 不相符直接终止
            }
        }
        // 第一行只能往右走
        for (int j = 1; j <= n; j++) {
            if (s2[j - 1] == s3[j - 1]) {
                dp[0][j] = true;
            } else {
                break; // 不相符直接终止
            }
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int k = i + j;
                bool s1Equals3 = (s1[i - 1] == s3[k - 1]) && dp[i - 1][j]
                bool s2Equals3 = (s2[j - 1] == s3[k - 1]) && dp[i][j - 1]
                dp[i][j] = s1Equals3 || s2Equals3;
            }
        }

        return dp[m][n]
    }
};