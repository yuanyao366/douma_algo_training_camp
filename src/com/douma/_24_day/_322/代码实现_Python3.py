from typing import List


class Solution:
    # 1. 贪心
    def coinChange1(self, coins: List[int], amount: int) -> int:
        coins.sort()
        rest, res = amount, 0
        for i in range(len(coins) - 1, -1, -1):
            # 需要当前面值多少个
            curr_count = rest // coins[i]
            # 还剩多少
            rest = rest - curr_count * coins[i]
            res += curr_count
            if rest == 0 :
                return res

        return -1

    # 2. 回溯
    def coinChange2(self, coins: List[int], amount: int) -> int:
        res, cs = [], []

        def dfs(target) -> int:
            if target < 0: return
            if target == 0:
                res.append(cs[:])
                return
            for i in range(len(coins)):
                cs.append(coins[i])
                dfs(target - coins[i])
                cs.pop()

        # 1. 回溯穷举所有的硬币组合
        dfs(amount)

        # 2. 没有任何的硬币组合，返回 -1
        if len(res) == 0:
            return -1

        # 3. 找到适应硬币数最少的组合的硬币数
        min_value = 0
        for i in range(1, len(res)):
            if len(res[i]) < len(res[min_value]):
                min_value = i

        return len(res[min_value])

    # 3. 回溯 (前序遍历)
    def coinChange3(self, coins: List[int], amount: int) -> int:
        min_coins, cs = 2**31 - 1, []

        def dfs(target) -> int:
            if target == 0:
                nonlocal min_coins
                min_coins = min(min_coins, len(cs))
                return
            for i in range(len(coins)):
                if target - coins[i] < 0:
                    continue
                cs.append(coins[i])
                dfs(target - coins[i])
                cs.pop()

        dfs(amount)

        return -1 if min_coins == 2**31 - 1 else min_coins

    # 回溯 + 贪心 不能提升性能
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 升序排列
        coins.sort()
        min_coins, cs = 2**31 - 1, []

        def dfs(target) -> int:
            if target == 0:
                nonlocal min_coins
                min_coins = min(min_coins, len(cs))
                return
            # 从最大的面值硬币开始 DFS
            for i in range(len(coins) - 1, -1, -1):
                if target - coins[i] < 0:
                    continue
                cs.append(coins[i])
                dfs(target - coins[i])
                cs.pop()

        dfs(amount)

        return -1 if min_coins == 2**31 - 1 else min_coins