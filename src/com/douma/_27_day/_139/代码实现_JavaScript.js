// 完全背包问题：
// 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s
var wordBreak1 = function(s, wordDict) {
    // dp[i]: 表示前 i 个字符组成的子串是否可以被 wordDict 中的字符串组合而成
    const dp = new Array(s.length + 1).fill(false)
    dp[0] = true

    // 注意：这里的组合的顺序是任意的，所以先选择字符，再选择每个字符串
    for (let i = 0; i <= s.length; i++) {
        for (const word of wordDict) {
            const wordLen = word.length
            if (i >= wordLen && s.substring(i - wordLen, i) == word) {
                dp[i] = dp[i] || dp[i - wordLen]
            }
        }
    }

    return dp[s.length]
};