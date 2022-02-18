// 动态规划(一)
func longestPalindromeSubseq(s string) int {
    var m = len(s)
    // 状态：dp[i][j] 表示在区间 [i...j] 中最长回文子序列的长度
    var dp = make([][]int, m)
    for i := range dp {
        dp[i] = make([]int, m)
    }

    // 初始化，一个字符的最长回文子序列长度是 1
    for i := 0; i < m; i++ {
        dp[i][i] = 1
    }

    for i := m - 2; i >= 0; i-- {
        for j := i + 1; j < m; j++ {
            if s[i] == s[j] {
                dp[i][j] = 2 + dp[i + 1][j - 1]
            } else {
                dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
            }
        }
    }

    return dp[0][m - 1]
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}