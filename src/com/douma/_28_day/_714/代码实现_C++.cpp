class Solution {
public:
    int maxProfit1(vector<int>& prices, int fee) {
        int len = prices.size();
        // 状态定义
        // dp[i][j] 表示第 i 天处于状态 j 获取到的最大利益
        // 其中 j 可以取值为： 0 表示不持股；1 表示持股
        vector<vector<int>> dp(len, vector<int>(2));

        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;

        for (int i = 1; i < len; i++) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }

        return dp[len - 1][0];
    }

    int maxProfit(vector<int>& prices, int fee) {
        int len = prices.size();

        int profit0 = 0;
        int profit1 = -prices[0] - fee;

        for (int i = 1; i < len; i++) {
            profit0 = max(profit0, profit1 + prices[i]);
            profit1 = max(profit1, profit0 - prices[i] - fee);
        }

        return profit0;
    }
};