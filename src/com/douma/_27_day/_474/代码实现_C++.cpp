class Solution {
public:
    // 二维费用背包问题
    // 物品是字符串数组中的字符串，选择每个字符串有两个代价，分别是 0 的个数和 1 的个数
    // 两个代价都有最大值，0 的个数最多为 m，1 的个数最多为 n
    // 求选择字符串得到的最大子集的大小
    int findMaxForm(vector<string>& strs, int m, int n) {
        // dp[i][j] 表示包含 i 个 0 和 j 个 1 的最大子集的大小
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));

        for (auto& s: strs) {
            vector<int> count = countzeroesones(s);
            int zeroes = count[0], ones = count[1];
            for (int zero = m; zero >= zeroes; zero--)
                for (int one = n; one >= ones; one--)
                    dp[zero][one] = max(1 + dp[zero - zeroes][one - ones], dp[zero][one]);
        }
        return dp[m][n];
    }

    vector<int> countzeroesones(string s) {
        vector<int> c(2);
        for (int i = 0; i < s.length(); i++) {
            c[s[i]-'0']++;
        }
        return c;
    }
};