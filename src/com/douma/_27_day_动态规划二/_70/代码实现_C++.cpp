class Solution {
public:
    // 1. 记忆化搜索
    int climbStairs1(int n) {
        vector<int> memo(n + 1, -1);
        return dfs(n, memo);
    }

    int dfs(int n, vector<int>& memo) {
        if (n == 1 || n == 2) return n;

        if (memo[n] != -1) return memo[n];

        int left = dfs(n - 1, memo);
        int right = dfs(n - 2, memo);

        memo[n] = left + right;
        return memo[n];
    }

    //2. 动态规划
    int climbStairs2(int n) {
        if (n == 1 || n == 2) return n;

        vector<int> dp(n + 1);

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    //2. 动态规划 + 状态压缩
    int climbStairs(int n) {
        if (n == 1 || n == 2) return n;
        int prev = 1;
        int curr = 2;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
};