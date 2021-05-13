from typing import List


class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        m = {}
        for num in nums:
            count = 1
            if num in m: count = m[num] + 1
            m[num] = count

        for key, value in m.items():
            if value == 1: return key
        return -1

    def singleNumber1(self, nums: List[int]) -> int:
        single = 0
        for num in nums:
            single ^= num
        return single