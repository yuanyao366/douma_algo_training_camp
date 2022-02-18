/**
 * @param {number[]} arr
 * @return {number}
 */
var lenLongestFibSubseq = function(arr) {
    const n = arr.length
    let ans = 0

    const indexes = new Map()
    for (let i = 0; i < n; i++) {
        indexes.set(arr[i], i)
    }

    // dp[i][j]：表示以 arr[i]，arr[j] 为结尾的最长的斐波那契子序列的长度
    const dp = new Array(n).fill(0).map(() => new Array(n).fill(0))

    for (let j = 0; j < n; j++) {
        for (let i = 0; i < j; i++) {
            const arrk = arr[j] - arr[i]
            if (indexes.has(arrk) && arrk < arr[i]) {
                const k = indexes.get(arrk)
                dp[i][j] = Math.max(dp[i][j], dp[k][i] + 1)
                ans = Math.max(ans, dp[i][j] + 2)
            }
        }
    }

    if (ans < 3) return 0
    return ans
};