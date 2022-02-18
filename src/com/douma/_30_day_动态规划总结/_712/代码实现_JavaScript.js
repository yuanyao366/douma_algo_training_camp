/**
 * @param {string} s1
 * @param {string} s2
 * @return {number}
 */
// 两个字符串的最小 ASCII 删除和等于两个字符串所有字母的和减去两个字符串最大公共子序列所有字母的和
    // 问题转化成 LCS 问题
var minimumDeleteSum = function(s1, s2) {
    const m = s1.length, n = s2.length

    let sum = 0
    for (const c of s1) sum += c.charCodeAt()
    for (const c of s2) sum += c.charCodeAt()

    // dp[i][j]：s1 前 i 个字符和 s2 前 j 个字符【最长公共子序列长度】
    const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0))

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (s1[i - 1] == s2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + s1[i - 1].charCodeAt()
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    return sum - 2 * dp[m][n]
};