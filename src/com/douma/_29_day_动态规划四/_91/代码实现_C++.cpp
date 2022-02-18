class Solution {
public:
    // 1. 记忆化搜索
    int numDecodings1(string s) {
        vector<int> memo(s.length() + 1, -1);
        return dfs(s, 0, memo);
    }

    // 以第 i 个字符开头的子串能解码的个数
    int dfs(string s, int i, vector<int>& memo) {
        if (i == s.length()) return 1;

        if (memo[i] != -1) return memo[i];

        if (s[i] != '0') {
            int ans1 = dfs(s, i + 1, memo);
            int ans2 = 0;
            if (i < s.length() - 1) {
                int one = s[i + 1] - '0';
                int ten = (s[i] - '0') * 10;
                if (one + ten <= 26) {
                    ans2 = dfs(s, i + 2, memo);
                }
            }
            memo[i] = ans1 + ans2;
        } else {
            memo[i] = 0;
        }

        return memo[i];
    }

    // 2. 动态规划(从右往左)
    int numDecodings2(string s) {
        int n = s.length();
        // dp[i]：表示以第 i 个字符开头的子串能解码的个数
        vector<int> dp(n + 1);

        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                dp[i] += dp[i + 1];
                if (i < n - 1) {
                    int one = s[i + 1] - '0';
                    int ten = (s[i] - '0') * 10;
                    if (one + ten <= 26) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }

    // 3. 记忆化搜索
    int numDecodings3(string s) {
        vector<int> memo(s.length() + 1, -1);
        return dfs2(s, s.length(), memo);
    }

    // 以第 i 个字符结尾的子串能解码的个数
    int dfs2(string s, int i, vector<int>& memo) {
        if (i == 0) return 1;
        if (memo[i] != -1) return memo[i];

        int ans1 = 0, ans2 = 0;
        if (s[i - 1] != '0') {
            ans1 = dfs2(s, i - 1, memo);
        }
        if (i > 1 && s[i - 2] != '0') {
            int one = s[i - 1] - '0';
            int ten = (s[i - 2] - '0') * 10;
            if (one + ten <= 26) {
                ans2 = dfs2(s, i - 2, memo);
            }
        }
        memo[i] = ans1 + ans2;
        return memo[i];
    }

    // 4. 动态规划(从左往右)
    int numDecodings(string s) {
        int n = s.length();
        // dp[i]：表示以第 i 个字符结尾的子串能解码的个数
        vector<int> dp(n + 1);

        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] != '0') {
                dp[i] += dp[i - 1];
            }

            if (i > 1 && s[i - 2] != '0') {
                int one = s[i - 1] - '0';
                int ten = (s[i - 2] - '0') * 10;
                if (one + ten <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
};