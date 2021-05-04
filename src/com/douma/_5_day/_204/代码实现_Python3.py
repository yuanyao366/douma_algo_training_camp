def countPrimes(self, n: int) -> int:
    ans = 0
    not_primes = [False] * n
    for x in range(2, n):
        if not_primes[x]: continue
        ans += 1
        # 如果 x 是质数，那么 2x、3x、4x.... 肯定不是质数
        for i in range(x, n, x):
            not_primes[i] = True
    return ans
