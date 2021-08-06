/**
 * @param {string} s
 * @return {number}
 */
var minCut = function(s) {
    // 这里是在 leetcode 647 之上，做了点改变
    const len = s.length
    // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
    const dp = new Array(len).fill(0).map(() => new Array(len).fill(false))
    // 状态初始化
    for (let i = 0; i < len; i++) dp[i][i] = true
    for (let j = 1; j < len; j++) {
        for (let i = 0; i < j; i++) {
            // [i, j]
            const isCharEqual = s[i] == s[j]
            if (j - i == 1) { // 只有两个字符
                dp[i][j] = isCharEqual
            } else  { // 大于两个字符
                dp[i][j] = isCharEqual && dp[i + 1][j - 1]
            }
        }
    }

    // f[i]：表示以 s[i] 结尾最少分割次数
    const f = new Array(len).fill(Math.pow(2, 31) - 1)
    for (let i = 0; i < len; i++) {
        if (dp[0][i]) { // s[0...i] 是回文串，那么不需要分割
            f[i] = 0
        } else {
            for (let j = 0; j < i; j++) {
                if (dp[j + 1][i]) { // s[j + 1...i] 是回文串，那么可以不分割
                    f[i] = Math.min(f[i], f[j] + 1)
                }
            }
        }
    }

    return f[len - 1]
};