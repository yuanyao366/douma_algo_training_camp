func longestPalindrome(s string) string {
    var n = len(s)
    if n == 1 {
        return s
    }
    // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
    var dp = make([][]bool, n)
    for i := range dp {
        dp[i] = make([]bool, n)
    }

    var res = string(s[0])
    // 状态初始化
    // 一个字符，肯定是回文
    for i := 0; i < n; i++ {
        dp[i][i] = true
    }

    // 状态转移
    for j := 1; j < n; j++ {
        for i := 0; i < j; i++ {
            // [i, j]
            var isCharEqual = s[i] == s[j]
            if j - i == 1 { // 只有两个字符
                dp[i][j] = isCharEqual
            } else { // 大于两个字符
                dp[i][j] = isCharEqual && dp[i + 1][j - 1]
            }
            if dp[i][j] && j - i + 1 > len(res) {
                res = s[i:j + 1]
            }
        }
    }

    return res
}