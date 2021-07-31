/**
 * @param {string} s
 * @return {number}
 */
// 1. 记忆化搜索
var numDecodings1 = function(s) {
    const n = s.length
    const memo = new Array(n + 1).fill(-1)

    // 以第 i 个字符开头的子串能解码的个数
    const dfs = (i) => {
        if (i == n) return 1
        if (memo[i] != -1) return memo[i]

        if (s[i] != '0') {
            const ans1 = dfs(i + 1)
            let ans2 = 0
            if (i < n - 1) {
                const one = s[i + 1].charCodeAt() - '0'.charCodeAt()
                const ten = (s[i].charCodeAt() - '0'.charCodeAt()) * 10
                if (one + ten <= 26) {
                    ans2 = dfs(i + 2)
                }
            }
            memo[i] = ans1 + ans2
        } else {
            memo[i] = 0
        }

        return memo[i]
    }

    return dfs(0)
};

// 2. 动态规划(从右往左)
var numDecodings2 = function(s) {
    const n = s.length
    // dp[i]：表示以第 i 个字符开头的子串能解码的个数
    const dp = new Array(n + 1).fill(0)
    dp[n] = 1
    for (let i = n - 1; i >= 0; i--) {
        if (s[i] != '0') {
            dp[i] += dp[i + 1]
            if (i < n - 1) {
                const one = s[i + 1].charCodeAt() - '0'.charCodeAt()
                const ten = (s[i].charCodeAt() - '0'.charCodeAt()) * 10
                if (one + ten <= 26) {
                    dp[i] += dp[i + 2]
                }
            }
        }
    }
    return dp[0]
}

// 3. 记忆化搜索
var numDecodings3 = function(s) {
    const n = s.length
    const memo = new Array(n + 1).fill(-1)

    const dfs = (i) => {
        if (i == 0) return 1
        if (memo[i] != -1) return memo[i]
        let ans1 = 0, ans2 = 0
        if (s[i - 1] != '0') {
            ans1 = dfs(i - 1)
        }
        if (i > 1 && s[i - 2] != '0') {
            const one = s[i - 1].charCodeAt() - '0'.charCodeAt()
            const ten = (s[i - 2].charCodeAt() - '0'.charCodeAt()) * 10
            if (one + ten <= 26) {
                ans2 = dfs(i - 2)
            }
        }
        memo[i] = ans1 + ans2
        return memo[i]
    }

    return dfs(n)
}

// 4. 动态规划(从左往右)
var numDecodings = function(s) {
    const n = s.length
    const dp = new Array(n + 1).fill(0)
    dp[0] = 1
    for (let i = 1; i <= n; i++) {
        if (s[i - 1] != '0') {
            dp[i] += dp[i - 1]
        }
        if (i > 1 && s[i - 2] != '0') {
            const one = s[i - 1].charCodeAt() - '0'.charCodeAt()
            const ten = (s[i - 2].charCodeAt() - '0'.charCodeAt()) * 10
            if (one + ten <= 26) {
                dp[i] += dp[i - 2]
            }
        }
    }
    return dp[n]
}