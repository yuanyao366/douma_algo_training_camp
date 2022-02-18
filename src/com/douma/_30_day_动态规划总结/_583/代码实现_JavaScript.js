/**
 * @param {string} word1
 * @param {string} word2
 * @return {number}
 */
/*
为了求得最少删除次数，我们可以求出串 word1 和串 word2 最长公共子序列，我们记为 lcs。
如果我们能求得 lcs 的值，我们可以轻易地求出答案，为 m + n - 2 * lcs。
这里 m 和 n 分别是给定字符串 word1 和 word2 的长度。

问题转化为 LCS 问题
    */
var minDistance = function(word1, word2) {
    const m = word1.length, n = word2.length

    // dp[i][j]：word1 前 i 个字符和 word2 前 j 个字符【最长公共子序列长度】
    const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0))

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (word1[i - 1] == word2[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1]
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    return m + n - 2 * dp[m][n]
};