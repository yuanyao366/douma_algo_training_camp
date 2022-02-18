/**
 * @param {string[]} strs
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
// 二维费用背包问题
// 物品是字符串数组中的字符串，选择每个字符串有两个代价，分别是 0 的个数和 1 的个数
// 两个代价都有最大值，0 的个数最多为 m，1 的个数最多为 n
// 求选择字符串得到的最大子集的大小
var findMaxForm = function(strs, m, n) {
    const countZerosOnes = (s) => {
        const res = new Array(2).fill(0)
        for (const c of s) {
            res[c.charCodeAt() - '0'.charCodeAt()]++
        }
        return res
    }

    const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0))
    for (const s of strs) {
        [zeros, ones] = countZerosOnes(s)
        for (let zero = m; zero >= zeros; zero--) {
            for (let one = n; one >= ones; one--) {
                dp[zero][one] = Math.max(dp[zero][one], 1 + dp[zero - zeros][one - ones])
            }
        }
    }

    return dp[m][n]
};