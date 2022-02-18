class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n < 0:
            x, n = 1 / x, -n
        return self.quickMul(x, n)

    def quickMul(self, x: float, n: int) -> float:
        if n == 0: return 1.0
        y = self.quickMul(x, n // 2)
        return y * y if n % 2 == 0 else y * y * x

    def myPow1(self, x: float, n: int) -> float:
        if n < 0:
            x, n = 1 / x, -n
        res = 1
        while n != 0:
            if n & 1: res *= x
            x *= x
            n >>= 1
        return res