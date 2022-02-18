/*
    为了求得最少删除次数，我们可以求出串 word1 和串 word2 最长公共子序列，我们记为 lcs。
    如果我们能求得 lcs 的值，我们可以轻易地求出答案，为 m + n - 2 * lcs。
    这里 m 和 n 分别是给定字符串 word1 和 word2 的长度。

    问题转化为 LCS 问题
*/
func minDistance(word1 string, word2 string) int {
    var m, n = len(word1), len(word2)

    // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
    var dp = make([][]int, m + 1)
    for i := range dp {
        dp[i] = make([]int, n + 1)
    }

    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if word1[i - 1] == word2[j - 1] {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
    }

    return m + n - 2 *  dp[m][n]
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}