from typing import List


class Solution:
    # 记石头的总重量为 sum，被粉碎的重量是 neg，那么没有粉碎的石头重量为 sum - neg
    # 最后一块石头的重量是：(sum - neg) - neg = sum - 2 * neg

    # 要使最后一块石头的重量尽可能地小，neg 需要在不超过 sum/2 的前提下尽可能地大

    # 因此本问题可以看作是背包容量为 sum/2，物品重量和价值均为 stones的 0-1 背包问题。
    def lastStoneWeightII(self, stones: List[int]) -> int:
        sum_ = sum(stones)
        # 背包的容量
        m = sum_ // 2

        # dp[c]：表示是否可以将石头放入到容量为 c 的背包中
        dp = [False] * (m + 1)
        dp[0] = True

        for stone in stones:
            for c in range(m, -1, -1):
                if c >= stone:
                    dp[c] = dp[c] or dp[c - stone]

        ans = None
        for i in range(m, -1, -1):
            if dp[i]:
                ans = sum_ - 2 * i
                break
        return ans