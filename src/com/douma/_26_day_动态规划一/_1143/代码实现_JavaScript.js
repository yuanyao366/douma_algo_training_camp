/**
 * @param {string} text1
 * @param {string} text2
 * @return {number}
 */
var longestCommonSubsequence1 = function(text1, text2) {
    const m = text1.length, n = text2.length

    // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
    const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0))

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (text1[i - 1] == text2[j - 1]) {
                dp[i][j] = 1 + dp[i - 1][j - 1]
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    return dp[m][n]
};

// 状态空间压缩
var longestCommonSubsequence2 = function(text1, text2) {
    const m = text1.length, n = text2.length

    if (m < n) return longestCommonSubsequence2(text2, text1)

    // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
    // 当前的状态依赖于前一行和当前行两行的状态值
    const dp = new Array(2).fill(0).map(() => new Array(n + 1).fill(0))

    for (let i = 1; i <= m; i++) {
        const currRow = i % 2
        for (let j = 1; j <= n; j++) {
            if (text1[i - 1] == text2[j - 1]) {
                dp[currRow][j] = 1 + dp[1 - currRow][j - 1]
            } else {
                dp[currRow][j] = Math.max(dp[1 - currRow][j], dp[currRow][j - 1])
            }
        }
    }

    return dp[m % 2][n]
};

// 状态空间继续压缩
var longestCommonSubsequence = function(text1, text2) {
    const m = text1.length, n = text2.length

    if (m < n) return longestCommonSubsequence(text2, text1)

    // dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
    // 当前的状态依赖于前一行和当前行两行的状态值
    const dp = new Array(n + 1).fill(0)

    for (let i = 1; i <= m; i++) {
        let preRow = 0, preRowPreCol = 0
        for (let j = 1; j <= n; j++) {
            preRowPreCol = preRow
            preRow = dp[j]
            if (text1[i - 1] == text2[j - 1]) {
                dp[j] = 1 + preRowPreCol
            } else {
                dp[j] = Math.max(preRow, dp[j - 1])
            }
        }
    }

    return dp[n]
};