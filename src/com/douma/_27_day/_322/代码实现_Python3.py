from typing import List


class Solution:
    # 转化为完全背包问题：
    #       从 coins 列表中可重复选择最少数量的硬币，使得他们的总金额为 amount
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 状态定义：dp[c] 表示凑齐总金额为 c 的时候需要的最小硬币数
        dp = [amount + 1] * (amount + 1)
        # 凑齐总金额为 0 的时候需要的最小硬币数就是不取硬币
        dp[0] = 0
        for coin in coins:
            for c in range(coin, amount + 1):
                dp[c] = min(dp[c], 1 + dp[c - coin])
        return dp[amount] if dp[amount] != amount + 1 else -1