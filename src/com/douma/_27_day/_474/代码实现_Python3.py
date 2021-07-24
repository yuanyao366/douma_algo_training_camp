from typing import List


class Solution:
    # 二维费用背包问题
    # 物品是字符串数组中的字符串，选择每个字符串有两个代价，分别是 0 的个数和 1 的个数
    # 两个代价都有最大值，0 的个数最多为 m，1 的个数最多为 n
    # 求选择字符串得到的最大子集的大小
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:

        def count_zeros_ones(s) -> List[int]:
            res = [0] * 2
            for c in s:
                res[ord(c) - ord('0')] += 1
            return res

        # dp[i][j] 表示包含 i 个 0 和 j 个 1 的最大子集的大小
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        for s in strs:
            count = count_zeros_ones(s)
            zeros, ones = count[0], count[1]
            for zero in range(m, zeros - 1, -1):
                for one in range(n, ones - 1, -1):
                    dp[zero][one] = max(dp[zero][one], 1 + dp[zero - zeros][one - ones])

        return dp[m][n]