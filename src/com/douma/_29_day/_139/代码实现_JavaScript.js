/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {boolean}
 */
// 完全背包问题：
// 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s
var wordBreak1 = function(s, wordDict) {
    // dp[i]: 表示前 i 个字符组成的子串是否可以被 wordDict 中的字符串组合而成
    const dp = new Array(s.length + 1).fill(false)
    dp[0] = true

    // 注意：这里的组合的顺序是任意的，所以先选择字符，再选择每个字符串
    for (let i = 0; i <= s.length; i++) {
        for (const word of wordDict) {
            const wordLen = word.length
            if (i >= wordLen && s.substring(i - wordLen, i) == word) {
                dp[i] = dp[i] || dp[i - wordLen]
            }
        }
    }

    return dp[s.length]
};

// 记忆化搜索(一)
var wordBreak2 = function(s, wordDict) {
    const dict = new Set(wordDict)
    const memo = new Map()

    // 以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
    const dfs = (i) => {
        if (i == s.length) return true

        if (memo.has(i)) return memo.get(i)

        for (let end = i + 1; end <= s.length; end++) {
            if (!dict.has(s.substring(i, end))) continue

            if (dfs(end)) {
                memo.set(i, true)
                return true
            }
        }

        memo.set(i, false)
        return false
    }

    return dfs(0)
}

// 动态规划(一)
var wordBreak3 = function(s, wordDict) {
    const dict = new Set(wordDict)
    // dp[i]：以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
    const dp = new Array(s.length + 1).fill(false)
    dp[s.length] = true

    // 如果 dp[j] == true 且 s(i, j] 存在于字典中，那么 dp[i] = true
    for (let i = s.length - 1; i >= 0; i--) {
        for (let j = i + 1; j <= s.length; j++) {
            if (dp[j] && dict.has(s.substring(i, j))) {
                dp[i] = true;
                break;
            }
        }
    }

    return dp[0]
}


// 记忆化搜索(二)
var wordBreak4 = function(s, wordDict) {
    const dict = new Set(wordDict)
    const memo = new Map()

    // 以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
    const dfs = (i) => {
        if (i == 0) return true

        if (memo.has(i)) return memo.get(i)

        for (let start = i - 1; start >= 0; start--) {
            if (!dict.has(s.substring(start, i))) continue

            if (dfs(start)) {
                memo.set(i, true)
                return true
            }
        }

        memo.set(i, false)
        return false
    }

    return dfs(s.length)
}


// 动态规划(二)
var wordBreak = function(s, wordDict) {
    const dict = new Set(wordDict)
    // dp[i]：以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
    const dp = new Array(s.length + 1).fill(false)
    dp[0] = true

    // 如果 dp[j] == true 且 s[j, i) 存在于字典中，那么 dp[i] = true
    for (let i = 0; i <= s.length; i++) {
        for (let j = 0; j < i; j++) {
            if (dp[j] && dict.has(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }

    return dp[s.length]
}