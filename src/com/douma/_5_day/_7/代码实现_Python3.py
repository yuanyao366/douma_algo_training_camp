def reverse(self, x: int) -> int:
    # 则其数值范围为 [−2^31,  2^31 − 1]
    boundry = 2 ** 31 - 1 if x > 0 else 2 ** 31
    y, res = abs(x), 0
    while y != 0:
        res = res * 10 + y % 10
        if res > boundry: return 0
        y //= 10
    return res if x > 0 else -res
