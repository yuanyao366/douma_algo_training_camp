/**
 * @param {string} s
 * @return {number}
 */
var longestPalindromeSubseq = function(s) {
    const m = s.length
    if (m == 0) return 0

    // 状态：dp[i][j] 表示在区间 [i...j] 中最长回文子序列的长度
    const dp = new Array(m).fill(0).map(() => new Array(m).fill(0))

    for (let i = 0; i < m; i++) {
        dp[i][i] = 1
    }

    for (let i = m - 2; i >= 0; i--) {
        for (let j = i + 1; j < m; j++) {
            if (s[i] == s[j]) {
                dp[i][j] = 2 + dp[i + 1][j - 1]
            } else {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
            }
        }
    }

    return dp[0][m - 1]
};