class Solution {
public:
    string longestPalindrome(string s) {
        if (s.length() == 0 || s.length() == 1) return s;
        int len = s.length();

        // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        vector<vector<bool>> dp(len, vector<bool>(len));

        string res = "";
        res += s[0];
        // 状态初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
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
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substr(i, j - i + 1);
                }
            }
        }

        return res;
    }
};