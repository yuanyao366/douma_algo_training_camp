from typing import List


class Solution:
    def lenLongestFibSubseq(self, arr: List[int]) -> int:
        n, ans = len(arr), 0
        indexes = {x: i for i, x in enumerate(arr)}
        # dp[i][j]：表示以 arr[i]，arr[j] 为结尾的最长的斐波那契子序列的长度
        dp = [[0] * n for _ in range(n)]
        for j in range(n):
            for i in range(j):
                arrk = arr[j] - arr[i]
                # 在 [0...i] 中找到一个元素 arr[k]，使得 arr[k] + arr[i] = arr[j]
                # 所以需要保证 arrk < arr[i]
                if arrk in indexes and arrk < arr[i]:
                    k = indexes[arrk]
                    dp[i][j] = max(dp[i][j], dp[k][i] + 1)
                    ans = max(ans, dp[i][j] + 2)
        if ans < 3: return 0
        return ans