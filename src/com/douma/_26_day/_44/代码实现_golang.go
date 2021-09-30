func isMatch(s string, p string) bool {
    var m, n = len(s), len(p)

    // 状态定义：dp[i][j] 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
    var dp = make([][]bool, m + 1)
    for i := range dp {
        dp[i] = make([]bool, n + 1)
    }

    // 状态初始化
    // 1. 空字符串和空字符串是匹配的
    dp[0][0] = true
    for i := 1; i <= n; i++ {
        // 3. 空字符串和 * 是匹配的
        if dp[0][i - 1] && p[i - 1] == '*' {
            dp[0][i] = true
        }
    }

    // 状态转移
    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if s[i - 1] == p[j - 1] || p[j - 1] == '?' {
                dp[i][j] = dp[i - 1][j - 1]
            } else if p[j - 1] == '*' {
                dp[i][j] = dp[i][j - 1] || dp[i - 1][j]
            }
        }
    }

    return dp[m][n]
}