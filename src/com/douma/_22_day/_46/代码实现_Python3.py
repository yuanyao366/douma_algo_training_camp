from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        res, path, used = [], [], [False] * len(nums)

        def dfs() -> None:
            if len(path) == len(nums):
                res.append(path[:])
                return

            for i in range(len(nums)):
                if used[i]: continue
                path.append(nums[i])
                used[i] = True
                dfs()
                path.pop()
                used[i] = False

        dfs()
        return res