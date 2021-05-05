def hammingWeight(self, n: int) -> int:
    res = 0
    while n != 0:
        n &= (n - 1)
        res += 1
    return res


def hammingWeight2(self, n: int) -> int:
    res = 0
    for i in range(32):
        res += n & 1
        n >>= 1
    return res


def hammingWeight1(self, n: int) -> int:
    res = 0
    for i in range(32):
        if (n & (1 << i)) != 0: res += 1
    return res
