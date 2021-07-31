/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {string[]}
 */
// 回溯
var wordBreak = function(s, wordDict) {
    const dict = new Set(wordDict)

    const dfs = (i) => {
        const res = []
        if (i == s.length) {
            res.push("")
            return res
        }

        for (let end = i + 1; end <= s.length; end++) {
            const word = s.substring(i, end)
            if (!dict.has(word)) continue;

            const list = dfs(end)
            for (const str of list) {
                const split = str == "" ? "" : " "
                res.push(word + split + str)
            }
        }

        return res
    }

    return dfs(0)
};