"""
0 - 1 背包：
有 n 种物品和一个容量为 C 的背包，
第 i 件物品的重量是 w[i]，价值是 v[i]，件数是 1 件
求将哪些物品装入背包可使得价值总和最大
"""
class Solution:
    def knapsack01(self, w, v, C):
        # 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        dp = [0] * (C + 1)

        # 2. 状态初始化

        # 3. 状态转移
        for i in range(len(w)):
            for c in range(C, w[i] - 1, -1):
                dp[c] = max(dp[c], v[i] + dp[c - w[i]])

        return dp[C]