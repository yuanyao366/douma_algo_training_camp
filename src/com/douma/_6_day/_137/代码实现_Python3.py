from typing import List


def singleNumber(self, nums: List[int]) -> int:
    seen_once = seen_twice = 0
    for num in nums:
        seen_once = ~seen_twice & (seen_once ^ num)
        seen_twice = ~seen_once & (seen_twice ^ num)
    return seen_once


def singleNumber(self, nums: List[int]) -> int:
    res = 0
    for i in range(32):
        one_count = sum((num >> i) & 1 for num in nums)
        if one_count % 3:
            # Python 这里对于最高位需要特殊判断
            if i == 31:
                res -= (1 << i)
            else:
                res |= (1 << i)
    return res