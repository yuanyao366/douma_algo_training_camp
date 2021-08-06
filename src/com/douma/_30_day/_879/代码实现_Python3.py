from typing import List


class Solution:
    # 二维费用背包问题
    #    选择一个工作 i ，需要两个代价：
    #        1. 人数减掉了 group[i]，人数最多为 n
    #        2. 利润增加 profit[i]，最少的利润为 minProfit
    def profitableSchemes(self, n: int, minProfit: int, group: List[int], profit: List[int]) -> int:
        MOD = 10**9 + 7

        # dp[j][k]：选择了 j 个员工，并且满足工作利润至少为 k 的情况下的盈利计划的总数目
        dp = [[0] * (minProfit + 1) for _ in range(n + 1)]
        for j in range(0, n + 1):
            dp[j][0] = 1

        for i in range(1, len(group) + 1):
            members, earn = group[i - 1], profit[i - 1]
            for j in range(n,  -1, -1):
                if j < members: continue
                for k in range(minProfit, -1, -1):
                    # 工作利润至少为 k 而不是工作利润恰好为 k
                    # 所以这个是 max(0, k - earn)，而非 k - earn，下面的代码相当于：

                    #if(k <= earn)
                    #    // 相当于当前的工作产生的利润完全满足第二个代价 (即已经满足工作利润至少为 k 的条件了)
                    #    dp[j][k] = dp[j][k] + dp[j - members][0];
                    #else
                    #    dp[j][k] = dp[j][k] + dp[j - members][k - earn];
                    dp[j][k] = (dp[j][k] + dp[j - members][max(0, k - earn)]) % MOD

        return dp[n][minProfit]