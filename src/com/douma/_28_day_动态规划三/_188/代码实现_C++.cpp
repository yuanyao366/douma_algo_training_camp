class Solution {
public:
    int maxProfit1(int k, vector<int>& prices) {
        int len = prices.size();
        if (k == 0 || len < 2) return 0;

        if ( k >= len / 2) {
            // 使用贪心算法求解
            return maxProfit(prices);
        }

        // 1. 状态定义
        // dp[i][k][s] : 表示到下标为 i 的天数为止(从 0 开始)，
        // 发生了 k 次交易次数(从 1 开始)，第 0 次交易的话，利润肯定是 0
        // 状态为 s 的最大利润
        // s = 1 表示持股，s = 0 表示不持股
        vector<vector<vector<int>>> dp(len, vector<vector<int>>(k + 1, vector<int>(2)));

        // 2. 状态初始化，所有的状态初始化为 0
        // 对于第一天：
        for (int j = 1; j <= k; j++) {
            // 第一天不持股的利润
            dp[0][j][0] = 0;
            // 第一天持股的利润
            dp[0][j][1] = -prices[0];
        }

        // 3. 状态转移
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[len - 1][k][0];
    }

    // 状态压缩
    int maxProfit(int k, vector<int>& prices) {
        int len = prices.size();
        if (k == 0 || len < 2) return 0;

        if ( k >= len / 2) {
            // 使用贪心算法求解
            return maxProfit(prices);
        }

        // 1. 状态定义
        // dp[i][k][s] : 表示到下标为 i 的天数为止(从 0 开始)，
        // 发生了 k 次交易次数(从 1 开始)，第 0 次交易的话，利润肯定是 0
        // 状态为 s 的最大利润
        // s = 1 表示持股，s = 0 表示不持股
        vector<vector<int>> dp(k + 1, vector<int>(2));

        // 2. 状态初始化，所有的状态初始化为 0
        // 对于第一天：
        for (int j = 1; j <= k; j++) {
            // 第一天不持股的利润
            dp[j][0] = 0;
            // 第一天持股的利润
            dp[j][1] = -prices[0];
        }

        // 3. 状态转移
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j][0] = max(dp[j][0], dp[j][1] + prices[i]);
                dp[j][1] = max(dp[j][1], dp[j - 1][0] - prices[i]);
            }
        }

        return dp[k][0];
    }

    int maxProfit(vector<int>& prices) {
        // 转换为股票系列的第 2 题，使用贪心算法完成，思路是只要有利润，就交易
        int res = 0;
        for (int i = 1; i < prices.size(); i++) {
            if (prices[i - 1] < prices[i]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
};