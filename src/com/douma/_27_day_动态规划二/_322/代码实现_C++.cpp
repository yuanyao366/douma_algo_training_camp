class Solution {
public:

    // 转化为完全背包问题：
     //     从 coins 列表中可重复选择最少数量的硬币，使得他们的总金额为 amount
    int coinChange(vector<int>& coins, int amount) {
        // 1. 状态定义：dp[c] 表示凑齐总金额为 c 的时候需要的最小硬币数
        vector<int> dp(amount + 1, amount + 1);

        // 2. 凑齐总金额为 0 的时候需要的最小硬币数就是不取硬币
        dp[0] = 0;

        // 3. 状态转移
        for (int i = 0; i < coins.size(); i++) {
            for (int c = coins[i]; c <= amount; c++) {
                dp[c] = min(dp[c], 1 + dp[c - coins[i]]);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }


};