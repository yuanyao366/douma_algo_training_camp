class Solution {
public:
    int maxProfit1(vector<int>& prices) {
        int len = prices.size();
        // 状态定义
        // dp[i][j] 表示第 i 天处于状态 j 获取到的最大利益
        // 其中 j 可以取值为： 0 表示不持股；1 表示持股
        vector<vector<int>> dp(len, vector<int>(2));

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);
        }

        return dp[len - 1][0];
    }

    int maxProfit(vector<int>& prices) {
        int prevProfit0 = 0;
        int profit0 = 0;
        int profit1 = -prices[0];

        for (int i = 1; i < prices.size(); i++) {
            int nextProfit0 = max(profit0, profit1 + prices[i]);
            int nextProfit1 = max(profit1, prevProfit0 - prices[i]);
            prevProfit0 = profit0;
            profit0 = nextProfit0;
            profit1 = nextProfit1;
        }

        return profit0;
    }
};