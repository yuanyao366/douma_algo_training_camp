// 完全背包问题：
// 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s 的组合
/*
物品：单词数组
背包重量：字符串
背包价值：单词是否组合成功字符串

dp[i][c],表示前 i 个单词，是否可以组成长度为 c 的字符串，
由于每个字符串都需所有的单词组合一遍，因此将背包重量的循环放到物品个数的循环之前。

每个物品都有放入和不放入的情况
当物品不放入背包
    dp[i][c]=dp[i - 1][c]
当物品放入背包
    dp[i][c]=dp[i][c - w.size()]
 */
// 压缩物品维度
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