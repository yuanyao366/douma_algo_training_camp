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
        // 提示：这里不可以不用判断 i < 2，原因是：
        // 目中的提示最后一条说了，如果是 * 的话，那么前面肯定有字符
        if (p[i - 1] == '*' && (i < 2 || dp[0][i - 2])) {
            dp[0][i] = true;
        }
    }

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                dp[i][j] = dp[i - 1][j - 1]
            } else if (p[j - 1] == '*') {
                // 注意，这里之所以不要判断 j 是否大于等于 2 的原因是：
                // 题目中的提示最后一条说了，如果是 * 的话，那么前面肯定有字符
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