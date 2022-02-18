class Solution:
    # DFS
    def isUgly1(self, n: int) -> bool:
        if n == 0: return False

        def dfs(n) -> bool:
            if n == 1: return True

            factors = [2, 3, 5]
            for factor in factors:
                if n % factor == 0 and dfs(n / factor):
                    return True

            return False

        return dfs(n)

    # 迭代
    def isUgly(self, n: int) -> bool:
        if n == 0: return False
        factors = [2, 3, 5]
        for factor in factors:
            while n % factor == 0:
                n //= factor
        return n == 1