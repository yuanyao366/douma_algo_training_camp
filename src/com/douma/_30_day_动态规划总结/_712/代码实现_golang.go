// 两个字符串的最小 ASCII 删除和等于两个字符串所有字母的和减去两个字符串最大公共子序列所有字母的和
// 问题转化成 LCS 问题
func minimumDeleteSum(s1 string, s2 string) int {
    var m, n = len(s1), len(s2)

    var sum = 0
    for _, c := range s1 {
        sum += int(c)
    }
    for _, c := range s2 {
        sum += int(c)
    }

    // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
    var dp = make([][]int, m + 1)
    for i := range dp {
        dp[i] = make([]int, n + 1)
    }

    for i := 1; i <= m; i++ {
        for j := 1; j <= n; j++ {
            if s1[i - 1] == s2[j - 1] {
                dp[i][j] = int(s1[i - 1]) + dp[i - 1][j - 1];
            } else {
                dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
    }

    return sum - 2 * dp[m][n]
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}