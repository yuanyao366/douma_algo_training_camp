def minFlips(self, a: int, b: int, c: int) -> int:
    aOrb = a | b
    equal = aOrb ^ c
    if equal == 0: return 0

    ans = 0
    for i in range(31):
        mask = 1 << i
        if (equal & mask) > 0:
            if (a & mask) == (b & mask) and (c & mask) == 0:
                ans += 2
            else:
                ans += 1
    return ans