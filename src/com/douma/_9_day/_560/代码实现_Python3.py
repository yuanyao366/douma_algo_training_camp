from typing import List


class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        mp = {0:1}
        res = prefixSum = 0
        for num in nums:
            prefixSum += num
            diff = prefixSum - k
            if diff in mp: res += mp[diff]
            mp[prefixSum] = mp.get(prefixSum, 0) + 1
        return res