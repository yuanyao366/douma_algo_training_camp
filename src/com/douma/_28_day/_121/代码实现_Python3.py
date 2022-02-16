from typing import List


class Solution:
    def maxProfit1(self, prices: List[int]) -> int:
        # 状态定义
        # dp[i][j] 表示第 i 天处于状态 j 获取到的最大利益
        # 其中 j 可以取值为： 0 表示不持股；1 表示持股
        dp = [[0] * 2 for _ in range(len(prices))]

        dp[0][0], dp[0][1] = 0, -prices[0]

        for i in range(1, len(prices)):
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
            '''
            // 在这里有不少同学会认为下面的一行代码等价于：dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 注意：不能直接这样将三维压成二维，如果是三维的话应该是这样的：
            // dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
            // 因为 dp[i - 1][0][0] = 0，所以代码变为：dp[i][1][1] = Math.max(dp[i - 1][1][1],  - prices[i]);
            // 这个时候才可以将三维压成二维，即变成下面的代码
            '''
            dp[i][1] = max(dp[i - 1][1], -prices[i])

        return dp[len(prices) - 1][0]

    def maxProfit(self, prices: List[int]) -> int:
        profit0, profit1 = 0, -prices[0]

        for i in range(1, len(prices)):
            profit0 = max(profit0, profit1 + prices[i])
            profit1 = max(profit1, -prices[i])

        return profit0