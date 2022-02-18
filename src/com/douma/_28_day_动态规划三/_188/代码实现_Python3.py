from typing import List


class Solution:
    def maxProfit1(self, k: int, prices: List[int]) -> int:
        length = len(prices)
        if k == 0 or length < 2: return 0

        if k >= length / 2:
            # 使用贪心算法求解
            return self.max_profit_help(prices)

        # 1. 状态定义
        # dp[i][k][s] : 表示到下标为 i 的天数为止(从 0 开始)，
        # 发生了 k 次交易次数(从 1 开始)，第 0 次交易的话，利润肯定是 0
        # 状态为 s 的最大利润
        # s = 1 表示持股，s = 0 表示不持股
        dp = [[[0] * 2 for _ in range(k + 1)] for _ in range(length)]

        # 2. 状态初始化，所有的状态初始化为 0
        # 对于第一天：
        for j in range(1, k + 1):
            # 第一天不持股的利润
            dp[0][j][0] = 0
            # 第一天持股的利润
            dp[0][j][1] = -prices[0]

        # 3. 状态转移
        for i in range(1, length):
            for j in range(1, k + 1):
                dp[i][j][0] = max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i])
                dp[i][j][1] = max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i])

        return dp[length - 1][k][0]


    # 状态压缩
    def maxProfit(self, k: int, prices: List[int]) -> int:
        length = len(prices)
        if k == 0 or length < 2: return 0

        if k >= length / 2:
            # 使用贪心算法求解
            return self.max_profit_help(prices)

        # 1. 状态定义
        # dp[i][k][s] : 表示到下标为 i 的天数为止(从 0 开始)，
        # 发生了 k 次交易次数(从 1 开始)，第 0 次交易的话，利润肯定是 0
        # 状态为 s 的最大利润
        # s = 1 表示持股，s = 0 表示不持股
        dp = [[0] * 2 for _ in range(k + 1)]

        # 2. 状态初始化，所有的状态初始化为 0
        # 对于第一天：
        for j in range(1, k + 1):
            # 第一天不持股的利润
            dp[j][0] = 0
            # 第一天持股的利润
            dp[j][1] = -prices[0]

        # 3. 状态转移
        for i in range(1, length):
            for j in range(1, k + 1):
                dp[j][0] = max(dp[j][0], dp[j][1] + prices[i])
                dp[j][1] = max(dp[j][1], dp[j - 1][0] - prices[i])

        return dp[k][0]

    def max_profit_help(self, prices: List[int]) -> int:
        res = 0
        for i in range(1, len(prices)):
            if prices[i] > prices[i - 1]:
                res += prices[i] - prices[i - 1]
        return res