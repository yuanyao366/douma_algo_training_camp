from typing import List


def plusOne(self, digits: List[int]) -> List[int]:
    for i in range(len(digits) - 1, -1, -1):
        digits[i] += 1
        digits[i] = digits[i] % 10
        if digits[i] != 0: return digits

    res = [0] * (len(digits) + 1)
    res[0] = 1
    return res