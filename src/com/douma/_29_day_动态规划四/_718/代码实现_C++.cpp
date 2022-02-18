class Solution {
public:
    int findLength(vector<int>& A, vector<int>& B) {
        int m = A.size(), n = B.size();
        // dp[i][j]：表示 A 中前 i 个元素中和 B 的前 j 个元素中最长公共子数组长度
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));

        int ans = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                ans = max(ans, dp[i][j]);
            }
        }

        return ans;
    }
};