from typing import List


class Solution:
    # 回溯 + 记忆化搜索
    def coinChange1(self, coins: List[int], amount: int) -> int:
        max_value = 2**31 - 1
        memo = [max_value] * (amount + 1)

        # 计算返回凑成总金额 target 需要的最少硬币数
        def dfs(target) -> int:
            if target == 0: return 0
            if memo[target] != max_value:
                return memo[target]
            min_coins = max_value
            for coin in coins:
                if target < coin: continue
                sub_min_coins = dfs(target - coin)
                if sub_min_coins == -1: continue
                min_coins = min(min_coins, sub_min_coins + 1)
            memo[target] = min_coins if min_coins != max_value else -1
            return memo[target]

        return dfs(amount)

    # 动态规划
    def coinChange(self, coins: List[int], amount: int) -> int:
        if amount < 0: return -1
        if amount == 0: return 0

        max_value = 2**31 - 1

        # 1. 状态定义：dp[i] 表示凑齐总金额为 i 的时候需要的最小硬币数
        # 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        dp = [max_value] * (amount + 1)

        # 2. 状态初始化
        dp[0] = 0

        # 3. 状态转移
        for target in range(1, amount + 1):
            for coin in coins:
                if target >= coin and dp[target - coin] != max_value:
                    dp[target] = min(dp[target], dp[target - coin] + 1)

        # 4. 返回最终需要的状态值
        return dp[amount] if dp[amount] != max_value else -1