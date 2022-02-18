from typing import List


def singleNumber(self, nums: List[int]) -> List[int]:
    bitmask = 0
    for num in nums: bitmask ^= num

    diff = bitmask & (-bitmask)

    ans = [0]*2
    for num in nums:
        if (num & diff) != 0:
            ans[0] ^= num
        else:
            ans[1] ^= num

    return ans