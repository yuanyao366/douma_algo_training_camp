// 完全背包问题：
// 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s 的组合
func wordBreak99(s string, wordDict []string) bool {
    var n = len(s)
    // dp[i]: 表示前 i 个字符组成的子串是否可以被 wordDict 中的字符串组合而成
    var dp = make([]bool, n + 1)

    dp[0] = true

    // 注意：这里的组合的顺序是任意的，所以先选择字符，再选择每个字符串
    // bug 修复：i 可以等于字符串的长度，因为 i 就是表示子串的长度
    for i := 1; i <= n; i++ {
        for _, word := range wordDict {
            var wordLen = len(word)
            if i >= wordLen && s[i - wordLen:i] == word {
                dp[i] = dp[i] || dp[i - wordLen]
            }
        }
    }

    return dp[n]
}

// 1. 记忆化搜索(一)
func wordBreak1(s string, wordDict []string) bool {
    var wordSet = make(map[string]bool)
    for _, word := range wordDict {
        wordSet[word] = true
    }

    var memo = make(map[int]bool)

    // 以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
    var dfs func(int) bool
    dfs = func(i int) bool {
        if i == len(s) {
            return true
        }
        if _, has := memo[i]; has {
            return memo[i]
        }

        for end := i + 1; end <= len(s); end++ {
            if !wordSet[s[i:end]] {
                continue
            }
            if dfs(end) {
                memo[i] = true
                return true
            }
        }

        memo[i] = false
        return false
    }

    return dfs(0)
}

// 2. 动态规划(一)
func wordBreak2(s string, wordDict []string) bool {
    var wordSet = make(map[string]bool)
    for _, word := range wordDict {
        wordSet[word] = true
    }

    var n = len(s)
    // 1. 定义状态，dp[i] 表示以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
    var dp = make([]bool, n + 1)

    // 2. 初始化
    // 因为空字符串总是字典的一部分。dp 数组剩余的元素都初始化为 false
    dp[n] = true

    // 3. 状态转移方程
    // 如果 dp[j] == true 且 s(i, j] 存在于字典中，那么 dp[i] = true
    for i := n - 1; i >= 0; i-- {
        for j := i + 1; j <= n; j++ {
            if !wordSet[s[i:j]] {
                continue
            }
            if dp[j] {
                dp[i] = true
                break
            }
        }
    }

    return dp[0]
}

// 3. 记忆化搜索(二)
func wordBreak3(s string, wordDict []string) bool {
    var wordSet = make(map[string]bool)
    for _, word := range wordDict {
        wordSet[word] = true
    }

    var memo = make(map[int]bool)

    // 以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
    var dfs func(int) bool
    dfs = func(i int) bool {
        if i == 0 {
            return true
        }
        if _, has := memo[i]; has {
            return memo[i]
        }

        for start := i - 1; start >= 0; start-- {
            if !wordSet[s[start:i]] {
                continue
            }
            if dfs(start) {
                memo[i] = true
                return true
            }
        }

        memo[i] = false
        return false
    }

    return dfs(len(s))
}

// 4. 动态规划(二)
func wordBreak(s string, wordDict []string) bool {
    var wordSet = make(map[string]bool)
    for _, word := range wordDict {
        wordSet[word] = true
    }

    var n = len(s)
    // 1. 定义状态，dp[i] 表示以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
    var dp = make([]bool, n + 1)

    // 2. 初始化
    // 因为空字符串总是字典的一部分。dp 数组剩余的元素都初始化为 false
    dp[0] = true

    // 3. 状态转移方程
    // 如果 dp[j] == true 且 s(i, j] 存在于字典中，那么 dp[i] = true
    for i := 1; i <= len(s); i++ {
        for j := i - 1; j >= 0; j-- {
            if !wordSet[s[j:i]] {
                continue
            }
            if dp[j] {
                dp[i] = true
                break
            }
        }
    }

    return dp[len(s)]
}