def hammingDistance(self, x: int, y: int) -> int:
    diff, res = x ^ y, 0
    while diff != 0:
        diff &= (diff - 1)
        res += 1
    return res