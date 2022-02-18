class Solution {
public:
    // 完全背包问题
    // 完全平方数最小为 1，最大为 sqrt(n)
    // 也就是我们要从 nums = [1, 2, ..., sqrt(n)] 数组里选出几个数，令其平方和为 target = n。
    // 转化为是否可以用 nums 中的数(可重复选用)组合和成 n
    int numSquares(int n) {
        // dp[i] 表示和为 i 的 nums 组合中完全平方数最少个数
        vector<int> dp(n + 1, INT_MAX);
        dp[0] = 0;

        for (int num = 1; num <= sqrt(n); num++) {
            for (int c = num * num; c <= n; c++) {
                dp[c] = min(dp[c], dp[c - num * num] + 1);
            }
        }

        return dp[n];
    }
};