class Solution:
    # DFS
    # 时间复杂度：O(2^n)
    # 因为 n 最大为 30，比较小，所以不会超时
    # 空间复杂度：O(logn) 递归系统栈需要的空间开销
    def fib1(self, n: int) -> int:
        def dfs(n) -> int:
            if n == 0: return 0
            if n == 1: return 1

            left_fib = dfs(n - 1)
            right_fib = dfs(n -2)

            return left_fib + right_fib

        return dfs(n)

    # DFS + 记忆化搜索
    # 时间复杂度：O(n)
    # 空间复杂度：O(n) 
    def fib2(self, n: int) -> int:
        memo = [-1] * (n + 1)

        def dfs(n) -> int:
            if n == 0: return 0
            if n == 1: return 1
            if memo[n] != -1:
                return memo[n]
            left_fib = dfs(n - 1)
            right_fib = dfs(n -2)
            memo[n] = left_fib + right_fib
            return memo[n]

        return dfs(n)

    # 动态规划
    def fib3(self, n: int) -> int:
        if n <= 1: return n
        # 1. 定义状态数组，dp[i] 表示的是数字 i 的斐波那契数
        dp = [0] * (n + 1)

        # 2. 状态初始化
        dp[0], dp[1] = 0, 1

        # 3. 状态转移
        for i in range(2, n + 1):
            dp[i] = dp[i - 1] + dp[i - 2]

        # 4. 返回最终需要的状态值
        return dp[n]


    # 动态规划 + 状态压缩
    def fib(self, n: int) -> int:
        if n <= 1: return n
        # 只存储前两个状态
        prev, curr = 0, 1

        for i in range(2, n + 1):
            sum = prev + curr
            prev, curr = curr, sum

        return curr