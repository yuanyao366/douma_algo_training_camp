/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function(s, p) {
    const m = s.length, n = p.length

    const dp = new Array(m + 1).fill(false).map(() => new Array(n + 1).fill(false))
    // 1. 空字符串和空字符串是匹配的
    dp[0][0] = true
    for (let i = 1; i <= m; i++) { // 第一列
        // 2. 空字符串和任何单个字符都不匹配
        dp[i][0] = false;
    }
    for (let i = 1; i <= n; i++) { // 第一行
        // 3. 前前一个元素匹配空字符串并且当前字符是 * ，那么是匹配
        if ((i < 2 || dp[0][i - 2]) && p[i - 1] == '*') {
            dp[0][i] = true;
        }
    }

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                dp[i][j] = dp[i - 1][j - 1]
            } else if (p[j - 1] == '*') {
                if (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 2]
                } else {
                    dp[i][j] = dp[i][j - 2]
                }
            }
        }
    }

    return dp[m][n]
};