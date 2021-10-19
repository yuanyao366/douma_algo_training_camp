# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

# 3. 动态规划
# 时间复杂度：O(n)
# 空间复杂度：O(1)
if __name__ == '__main__':
    n = int(input())
    if n == 1:
        print(2)
        exit(0)
    if n == 2:
        print(4)
        exit(0)

    prev, curr = 2, 4
    for i in range(3, n + 1):
        tmp = (prev + curr) % 998244353
        prev = curr
        curr = tmp;
    print(curr)

# 2. 动态规划
# 时间复杂度：O(n)
# 空间复杂度：O(n)
if __name__ == '__main2__':
    n = int(input())
    if n == 1:
        print(2)
        exit(0)
    if n == 2:
        print(4)
        exit(0)

    dp = [-1] * (n + 1)
    dp[1], dp[2] = 2, 4
    for i in range(3, n + 1):
        dp[i] = (dp[i - 1] + dp[i - 2]) % 998244353
    print(dp[n])


# 1. 记忆化搜索
# 时间复杂度：O(n)
# 空间复杂度：O(n)
if __name__ == '__main11__':
    n = int(input())
    memo = [-1] * (n + 1)

    def dfs(k):
        if k == 1:
            return 2
        if k == 2:
            return 4
        if memo[k] != -1:
            return memo[k]
        memo[k] = (dfs(k - 1) + dfs(k - 2)) % 998244353
        return memo[k]

    print(dfs(n))