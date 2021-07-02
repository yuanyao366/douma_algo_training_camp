from typing import List


class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res, subset = [], []

        def findSubsets(startIndex) -> None:
            res.append(subset[:])
            for i in range(startIndex, len(nums)):
                subset.append(nums[i])
                findSubsets(i + 1)
                subset.pop()

        findSubsets(0)
        return res