from typing import List


class Solution:
    def maxEnvelopes(self, envelopes: List[List[int]]) -> int:
        envelopes.sort(key=lambda x: (x[0], -x[1]))
        n = len(envelopes)
        dp = [1] * n
        res = 1
        for j in range(1, n):
            for i in range(0, j):
                if envelopes[j][1] > envelopes[i][1]:
                    dp[j] = max(dp[i] + 1, dp[j])
                    res = max(res, dp[j])

        return res