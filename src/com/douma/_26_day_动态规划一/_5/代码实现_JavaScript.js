/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function(s) {
    if (s.length == 0 || s.length == 1) return s
    const len = s.length

    // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
    const dp = new Array(len).fill(0).map(() => new Array(len).fill(false))

    let res = s[0]
    // 状态初始化
    for (let i = 0; i < len; i++) {
        dp[i][i] = true
    }

    // 状态转移
    for (let j = 1; j < len; j++) {
        for (let i = 0; i < j; i++) {
            // [i, j]
            const isCharEqual = s[i] == s[j]
            if (j - i == 1) { // 只有两个字符
                dp[i][j] = isCharEqual
            } else  { // 大于两个字符
                dp[i][j] = isCharEqual && dp[i + 1][j - 1]
            }
            if (dp[i][j] && j - i + 1 > res.length) {
                res = s.substring(i, j + 1)
            }
        }
    }
    return res
};