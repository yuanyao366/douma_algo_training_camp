/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function(n) {
    const res = []
    if (n <= 0) return res

    const dfs = (path, open, close) => {
        if (open > n || close > open) return
        if (path.length == 2 * n) {
            res.push(path)
            return
        }
        dfs(path + "(", open + 1, close)
        dfs(path + ")", open, close + 1)
    }

    dfs("", 0, 0)
    return res
};