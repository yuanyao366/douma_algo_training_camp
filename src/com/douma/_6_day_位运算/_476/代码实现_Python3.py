def findComplement(self, num: int) -> int:
    mask = ~0
    while (num & mask) > 0:
        mask <<= 1
    return ~mask ^ num