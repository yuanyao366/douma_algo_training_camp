// 完全背包问题：
// 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s 的组合
func wordBreak(s string, wordDict []string) bool {
    var n = len(s)
    // dp[i]: 表示前 i 个字符组成的子串是否可以被 wordDict 中的字符串组合而成
    var dp = make([]bool, n + 1)

    dp[0] = true

    // 注意：这里的组合的顺序是任意的，所以先选择字符，再选择每个字符串
    // bug 修复：i 可以等于字符串的长度，因为 i 就是表示子串的长度
    for i := 1; i <= n; i++ {
        for _, word := range wordDict {
            var wordLen = len(word)
            if i >= wordLen && s[i - wordLen:i] == word {
                dp[i] = dp[i] || dp[i - wordLen]
            }
        }
    }

    return dp[n]
}