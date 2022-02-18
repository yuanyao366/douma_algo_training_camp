class Solution {
public:
    // 动态规划
    // 时间复杂度：O(n^2)
    int countSubstrings(string s) {
        if (s.length() == 0) return 0;
        int len = s.length();

        // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        vector<vector<bool>> dp(len, vector<bool>(len));

        int res = 0;
        // 状态初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            res++;
        }

        // 状态转移
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                bool isCharEqual = s[i] == s[j];
                if (j - i == 1) { // 只有两个字符
                    dp[i][j] = isCharEqual;
                } else {
                    dp[i][j] = isCharEqual && dp[i + 1][j - 1];
                }
                if (dp[i][j]) res++;
            }
        }

        return res;
    }
};