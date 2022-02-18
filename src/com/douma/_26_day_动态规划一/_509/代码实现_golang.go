// DFS
// 时间复杂度：O(2^n)
// 因为 n 最大为 30，比较小，所以不会超时
// 空间复杂度：O(logn) 递归系统栈需要的空间开销
func fib1(n int) int {
    var dfs func(int) int
    dfs = func(n int) int {
        if n == 0 {
            return 0
        }
        if n == 1 {
            return 1
        }

        var leftFib = dfs(n - 1)
        var rightFib = dfs(n -2)
        return leftFib + rightFib
    }

    return dfs(n)
}

// DFS + 记忆化搜索
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func fib2(n int) int {
    var memo = make([]int, n + 1)
    for i := range memo {
        memo[i] = -1
    }

    var dfs func(int) int
    dfs = func(n int) int {
        if n == 0 {
            return 0
        }
        if n == 1 {
            return 1
        }
        if memo[n] != -1 {
            return memo[n]
        }
        var leftFib = dfs(n - 1)
        var rightFib = dfs(n -2)
        memo[n] = leftFib + rightFib
        return memo[n]
    }

    return dfs(n)
}

// 动态规划
func fib3(n int) int {
    if n <= 1 {
        return n
    }

    // 1. 定义状态数组，dp[i] 表示的是数字 i 的斐波那契数
    var dp = make([]int, n + 1)

    // 2. 状态初始化
    dp[0] = 0
    dp[1] = 1

    // 3. 状态转移
    for i := 2; i <= n; i++ {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    // 4. 返回最终需要的状态值
    return dp[n]
}

// 动态规划 + 状态压缩
func fib(n int) int {
    if n <= 1 {
        return n
    }

    var prev, curr = 0, 1

    for i := 2; i <= n; i++ {
        var sum = prev + curr
        prev = curr
        curr = sum
    }

    return curr
}