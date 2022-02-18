func minDistance(word1 string, word2 string) int {
    var m, n = len(word1), len(word2)

    // bug 修复，如果有一个字符为空字符串，就可以提前返回另一个字符串的长度
    if m * n == 0 {
        return m + n
    }

    // dp[i][j] 表示 word1 前 i 个字符转换成 word2 前 j 个字符花的最少操作数[即编辑距离]
    var dp = make([][]int, m + 1)
    for i := range dp {
        dp[i] = make([]int, n + 1)
    }

    // bug 修复：i 可以等于 m 或者 n
    for i := 0; i <= m; i++ {
        dp[i][0] = i
    }
    for i := 0; i <= n; i++ {
        dp[0][i] = i
    }

    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if word1[i - 1] == word2[j - 1] {
                dp[i][j] = dp[i - 1][j - 1]
            } else {
                var insertNum = 1 + dp[i][j - 1]
                var deleteNum = 1 + dp[i - 1][j]
                var replaceNum = 1 + dp[i - 1][j - 1]
                dp[i][j] = min(insertNum, min(deleteNum, replaceNum))
            }
        }
    }

    return dp[m][n]
}

func min(a, b int) int {
    if a < b {
        return a
    }
    return b
}