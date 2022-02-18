from typing import List


class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        candidates.sort()

        res, combination = [], []

        def dfs(start, target) -> None:
            if target < 0:
                return
            if target == 0:
                res.append(combination[:])
                return

            for i in range(start, len(candidates)):
                # 为了保证数组元素访问的顺序，所以 i > start
                if i > start and candidates[i] == candidates[i - 1]:
                    continue
                combination.append(candidates[i])
                dfs(i + 1, target - candidates[i])
                combination.pop()

        dfs(0, target)
        return res