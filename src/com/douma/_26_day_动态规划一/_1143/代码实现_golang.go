func longestCommonSubsequence1(text1 string, text2 string) int {
    var m, n = len(text1), len(text2)

    // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
    var dp = make([][]int, m + 1)
    for i := range dp {
        dp[i] = make([]int, n + 1)
    }

    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if text1[i - 1] == text2[j - 1] {
                dp[i][j] = 1 + dp[i - 1][j - 1]
            } else {
                dp[i][j] = max(dp[i][j - 1], dp[i - 1][j])
            }
        }
    }

    return dp[m][n]
}

// 状态压缩
func longestCommonSubsequence2(text1 string, text2 string) int {
    var m, n = len(text1), len(text2)

    // 调换字符串顺序可以减少空间的使用
    if m < n {
        return longestCommonSubsequence(text2, text1)
    }

    // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
    // 当前的状态依赖于前一行和当前行两行的状态值
    var dp = make([][]int, 2)
    for i := range dp {
        dp[i] = make([]int, n + 1)
    }

    for i := 1; i <= m; i++ {
        var currRow = i % 2
        for j := 1; j <= n; j++ {
            if text1[i - 1] == text2[j - 1] {
                dp[currRow][j] = 1 + dp[1 - currRow][j - 1]
            } else {
                dp[currRow][j] = max(dp[currRow][j - 1], dp[1 - currRow][j])
            }
        }
    }

    return dp[m % 2][n]
}

// 状态继续压缩
func longestCommonSubsequence(text1 string, text2 string) int {
    var m, n = len(text1), len(text2)

    // 调换字符串顺序可以减少空间的使用
    if m < n {
        return longestCommonSubsequence(text2, text1)
    }

    // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
     // 当前的状态依赖于前一行和当前行两行的状态值，我们可以使用两个变量来存储
    var dp = make([]int, n + 1)

    for i := 1; i <= m; i++ {
        var preRow, preRowPreCol = 0, 0
        for j := 1; j <= n; j++ {
            preRowPreCol = preRow
            preRow = dp[j]
            if text1[i - 1] == text2[j - 1] {
                dp[j] = 1 + preRowPreCol
            } else {
                dp[j] = max(preRow, dp[j - 1])
            }
        }
    }

    return dp[n]
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}