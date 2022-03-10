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
            """
            【有符号整数类型】的最高位，也就是第 31 位是表示符号位，如果最高一位是 1 的话，那么表示的是 -2^31
            而【无符号整数类型】的最高位，也就是第 31 位不是符号位，如果最高一位是 1 的话，那么表示 2^31
            pothon 对【有符号整数类型】和【无符号整数类型】是没有区分，所以需要区分第 31 位，如果是 1 的话，那么需要减掉 -2^31
            """
            if i == 31:
                res -= (1 << i)
            else:
                res |= (1 << i)
    return res