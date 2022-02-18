from typing import List


class Solution:
    # 转化为完全背包问题：
    #    从 coins 列表中可重复选择硬币组合，使得他们的总金额为 amount
    #    请问有多少种组合
    def change(self, amount: int, coins: List[int]) -> int:
        # dp[c]：硬币列表能够凑成总金额为 c 的组合数。
        dp = [0] * (amount + 1)
        # 凑成总金额为 0 的组合就是不选择任何硬币
        dp[0] = 1

        for coin in coins:
            for c in range(coin, amount + 1):
                dp[c] = dp[c] + dp[c - coin]

        return dp[amount]