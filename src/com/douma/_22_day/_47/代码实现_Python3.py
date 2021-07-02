from typing import List


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        # 排序，去重的基础
        nums.sort()
        res, path, used = [], [], [False] * len(nums)

        def dfs() -> None:
            if len(path) == len(nums):
                res.append(path[:])
                return

            for i in range(len(nums)):
                if used[i]: continue
                # 去重的条件
                if i > 0 and nums[i] == nums[i - 1] and not used[i - 1]:
                    continue
                path.append(nums[i])
                used[i] = True
                dfs()
                path.pop()
                used[i] = False

        dfs()
        return res