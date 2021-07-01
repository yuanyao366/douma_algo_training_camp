/**
 * @param {number} n
 * @return {boolean}
 */

// DFS
var isUgly1 = function(n) {
    if (n == 0) return false

    const dfs = (n) => {
        if (n == 1) return true

        const factors = [2, 3, 5]
        for (const factor of factors) {
            if (n % factor == 0 && dfs(Math.floor(n / factor))) {
                return true
            }
        }
        return false
    }

    return dfs(n)
};


// 迭代
var isUgly = function(n) {
    if (n == 0) return false
    const factors = [2, 3, 5]
    for (const factor of factors) {
        while (n % factor == 0) {
            n = Math.floor(n / factor)
        }
    }
    return n == 1
}