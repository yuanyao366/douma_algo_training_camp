class Solution {
public:
    // 回溯 + 记忆化搜索
    int coinChange1(vector<int>& coins, int amount) {
        vector<int> memo(amount + 1, INT_MAX);
        return dfs(amount, coins, memo);
    }

    // 计算返回凑成总金额 target 需要的最少硬币数
    int dfs(int target, vector<int>& coins, vector<int>& memo) {
        if (target == 0) return 0;
        if (memo[target] != INT_MAX) return memo[target];

        int minCoins = INT_MAX;
        for (int i = 0; i < coins.size(); i++) {
            if (target - coins[i] < 0) continue;
            int subMinCoins = dfs(target - coins[i], coins, memo);
            if (subMinCoins == -1) continue;
            minCoins = min(minCoins, subMinCoins + 1);
        }

        memo[target] = minCoins == INT_MAX ? -1 : minCoins;
        return memo[target];
    }

    // DP
    // O(amount * n) n 表示硬币的种类
    int coinChange(vector<int>& coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        // 1. 状态定义：dp[i] 表示凑齐总金额为 i 的时候需要的最小硬币数
        // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        vector<int> dp(amount + 1, INT_MAX);

        // 2. 状态初始化
        dp[0] = 0;

        // 3. 状态转移
        for (int target = 1; target <= amount; target++) {
            for (int coin : coins) {
                if (target >= coin && dp[target - coin] != INT_MAX) {
                    dp[target] = min(dp[target], dp[target - coin] + 1);
                }
            }
        }

        // 4. 返回最终需要的状态值
        return dp[amount] != INT_MAX ? dp[amount] : -1;
    }


};