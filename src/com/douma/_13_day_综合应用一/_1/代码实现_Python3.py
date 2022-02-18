from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_index_map = dict()

        for i in range(len(nums)):
            x = nums[i]
            if (target - x) in num_index_map:
                index = num_index_map[target - x]
                return [i, index]
            num_index_map[x] = i

        return []

    def twoSum2(self, nums: List[int], target: int) -> List[int]:
        num_index_map = {x: i for i, x in enumerate(nums)}

        for i in range(len(nums)):
            x = nums[i]
            if (target - x) in num_index_map:
                index = num_index_map[target - x]
                if i != index:
                    return [i, index]

        return []

    def twoSum1(self, nums: List[int], target: int) -> List[int]:

        for i in range(len(nums)):
            x = nums[i]
            for j in range(i + 1, len(nums)):
                if nums[j] == target - x:
                    return [i, j]
        return []