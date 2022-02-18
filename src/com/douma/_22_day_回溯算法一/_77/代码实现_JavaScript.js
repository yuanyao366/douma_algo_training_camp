/**
 * @param {number} n
 * @param {number} k
 * @return {number[][]}
 */
var combine = function(n, k) {
    if (n <= 0 || k <= 0 || k > n) return []

    const res = [], combination = []

    const dfs = (start) => {
        if (combination.length == k) {
            res.push(combination.slice())
            return
        }

        for (let i = start; i <= n; i++) {
            combination.push(i)
            dfs(i + 1)
            combination.pop()
        }
    }

    dfs(1)
    return res
};