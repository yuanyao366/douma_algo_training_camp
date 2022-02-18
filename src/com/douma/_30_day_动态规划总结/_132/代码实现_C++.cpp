class Solution {
public:
    int minCut(string s) {

        // 这里是在 leetcode 647 之上，做了点改变
        int len = s.length();
        // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        vector<vector<bool>> dp(len, vector<bool>(len));
        // 状态初始化
        for (int i = 0; i < len; i++) dp[i][i] = true;

        // 状态转移
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                bool isCharEqual = s[i] == s[j];
                if (j - i == 1) { // 只有两个字符
                    dp[i][j] = isCharEqual;
                } else {
                    dp[i][j] = isCharEqual && dp[i + 1][j - 1];
                }
            }
        }

        // f[i]：表示以 s[i] 结尾最少分割次数
        vector<int> f(len, INT_MAX);
        for (int i = 0; i < len; i++) {
            if (dp[0][i]) { // s[0...i] 是回文串，那么不需要分割
                f[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (dp[j + 1][i]) { // s[j + 1...i] 是回文串，那么可以不分割
                        f[i] = min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[len - 1];
    }
};