// 1. 记忆化搜索
func numDecodings1(s string) int {
    var memo = make([]int, len(s))
    for i := range memo {
        memo[i] = -1
    }

    // 以第 i 个字符开头的子串能解码的个数
    var dfs func(int) int
    dfs = func(i int) int {
        if i == len(s) {
            return 1
        }
        if memo[i] != -1 {
            return memo[i]
        }
        if s[i] == '0' {
            return 0
        }

        var res = 0
        res += dfs(i + 1)
        if i < len(s) - 1 {
            var one = int(s[i + 1] - '0')
            var ten = int(s[i] - '0') * 10
            if one + ten <= 26 {
                res += dfs(i + 2)
            }
        }

        memo[i] = res
        return memo[i]
    }

    return dfs(0)
}

// 2. 动态规划(从右往左)
func numDecodings(s string) int {
    var n = len(s)
    // dp[i]：表示以第 i 个字符开头的子串能解码的个数
    var dp = make([]int, n + 1)

    dp[n] = 1

    for i := n - 1; i >= 0; i-- {
        if s[i] != '0' {
            dp[i] += dp[i + 1]
            if i < n - 1 {
                var one = int(s[i + 1] - '0')
                var ten = int(s[i] - '0') * 10
                if one + ten <= 26 {
                    dp[i] += dp[i + 2]
                }
            }
        }
    }

    return dp[0]
}