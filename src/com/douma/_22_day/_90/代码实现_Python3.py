from typing import List


class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        res, subset = [], []

        def findSubsets(startIndex) -> None:
            res.append(subset[:])
            for i in range(startIndex, len(nums)):
                # i > startIndex 的目的就是为了排除 i == startIndex 的情况，也就是保证 i 不是第一个子节点
                # 因为第一个子节点前面没有节点的，那么 nums[i] == nums[i - 1] 就没有意义的
                if i > startIndex and nums[i] == nums[i - 1]:
                    continue
                subset.append(nums[i])
                findSubsets(i + 1)
                subset.pop()

        findSubsets(0)
        return res