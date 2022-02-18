class Solution {
public:
    // 1，记忆化搜索
    int minCostClimbingStairs1(vector<int>& cost) {
        int n = cost.size();
        vector<int> memo(n + 1, -1);
        return dfs(cost, n, memo);
    }

    int dfs(vector<int> cost, int i, vector<int>& memo) {
        if (i == 0 || i == 1) return 0;

        if (memo[i] != -1) return memo[i];

        int left = dfs(cost, i - 1, memo);
        int right = dfs(cost, i - 2, memo);

        memo[i] = min(left + cost[i - 1], right + cost[i - 2]);
        return memo[i];
    }

    // 2. 动态规划
    int minCostClimbingStairs2(vector<int>& cost) {
        int n = cost.size();

        vector<int> dp(n + 1);

        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[n];
    }

    // 3. 动态规划 + 状态压缩
    int minCostClimbingStairs(vector<int>& cost) {
        int n = cost.size();

        int prev = 0;
        int curr = 0;

        for (int i = 2; i <= n; i++) {
            int minCost = min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = minCost;
        }

        return curr;
    }
};