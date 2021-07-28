from typing import List


class Solution:
    def maxProfit1(self, prices: List[int], fee: int) -> int:
        # 状态定义
        # dp[i][j] 表示第 i 天处于状态 j 获取到的最大利益
        # 其中 j 可以取值为： 0 表示不持股；1 表示持股
        dp = [[0] * 2 for _ in range(len(prices))]

        dp[0][0], dp[0][1] = 0, -prices[0] - fee

        for i in range(1, len(prices)):
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)

        return dp[len(prices) - 1][0]

    def maxProfit(self, prices: List[int], fee: int) -> int:
        profit0, profit1 = 0, -prices[0] - fee

        for i in range(1, len(prices)):
            profit0 = max(profit0, profit1 + prices[i])
            profit1 = max(profit1, profit0 - prices[i] - fee)

        return profit0