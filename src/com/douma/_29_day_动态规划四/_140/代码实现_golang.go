func wordBreak(s string, wordDict []string) []string {
    var wordSet = make(map[string]bool)
    for _, word := range wordDict {
        wordSet[word] = true
    }

    // 以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
    var dfs func(int) []string
    dfs = func(i int) []string {
        var res = make([]string, 0)
        if i == len(s) {
            res = append(res, "")
            return res
        }

        for end := i + 1; end <= len(s); end++ {
            var word = s[i:end]
            if !wordSet[word] {
                continue
            }
            var list = dfs(end)
            for _, str := range list {
                var d = " "
                if str == "" {
                    d = ""
                }
                res = append(res, word + d + str)
            }
        }
        return res
    }

    return dfs(0)
}