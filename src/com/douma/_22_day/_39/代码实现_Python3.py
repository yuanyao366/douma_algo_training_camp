from typing import List


class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        res, combination = [], []

        def dfs(start, target) -> None:
            if target < 0:
                return
            if target == 0:
                res.append(combination[:])
                return

            for i in range(start, len(candidates)):
                combination.append(candidates[i])
                dfs(i, target - candidates[i])
                combination.pop()

        dfs(0, target)
        return res