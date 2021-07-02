from typing import List


class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        if n <= 0 or k <= 0 or k > n:
            return []

        res, combination = [], []

        def dfs(start) -> None:
            if len(combination) == k:
                res.append(combination[:])
                return

            for i in range(start, n + 1):
                combination.append(i)
                dfs(i + 1)
                combination.pop()

        dfs(1)
        return res