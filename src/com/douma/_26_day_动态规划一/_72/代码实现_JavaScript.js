/**
 * @param {string} word1
 * @param {string} word2
 * @return {number}
 */
var minDistance = function(word1, word2) {
    const m = word1.length, n = word2.length
    if (m * n == 0) return m + n

    // dp[i][j] 表示 word1 前 i 个字符转换成 word2 前 j 个字符花的最少操作数[即编辑距离]
    const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0))

    for (let i = 0; i <= m; i++) {
        dp[i][0] = i
    }
    for (let i = 0; i <= n; i++) {
        dp[0][i] = i
    }

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (word1[i - 1] == word2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1]
            } else {
                const insertNum = 1 + dp[i][j - 1]
                const deleteNum = 1 + dp[i - 1][j]
                const replaceNum = 1 + dp[i - 1][j - 1]
                dp[i][j] = Math.min(insertNum, Math.min(deleteNum, replaceNum))
            }
        }
    }

    return dp[m][n]
};