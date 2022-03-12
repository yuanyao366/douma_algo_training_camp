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
        // 前前一个元素匹配空字符串并且当前字符是 * ，那么是匹配
        // 提示：这里不可以不用判断 i < 2，原因是：
        // 目中的提示最后一条说了，如果是 * 的话，那么前面肯定有字符
        if p[i - 1] == '*' && (i < 2 || dp[0][i - 2]) {
            dp[0][i] = true
        }
    }

    // 状态转移
    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if s[i - 1] == p[j - 1] || p[j - 1] == '.' {
                dp[i][j] = dp[i - 1][j - 1]
            } else if p[j - 1] == '*' {
               // 需要再往回看一个
               // 注意，这里之所以不要判断 j 是否大于等于 2 的原因是：
               // 题目中的提示最后一条说了，如果是 * 的话，那么前面肯定有字符
               if s[i - 1] == p[j - 2] || p[j - 2] == '.' {
                   dp[i][j] = dp[i - 1][j] || dp[i][j - 2]
               } else {
                   dp[i][j] = dp[i][j - 2]
               }
            }
        }
    }

    return dp[m][n]
}